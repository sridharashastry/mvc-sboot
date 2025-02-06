package com.bring.orders.service;


import com.bring.orders.dto.InventoryFullData;
import com.bring.orders.dto.InventoryResponse;
import com.bring.orders.dto.OrderLineItemDto;
import com.bring.orders.dto.OrderRequest;
import com.bring.orders.events.OrderPlacedEvent;
import com.bring.orders.models.Order;
import com.bring.orders.models.OrderLineItem;
import com.bring.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;


@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    //@Autowired
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


    public List<Order> getAllOrders(){
       return orderRepository.findAll();
    }


    public void checkInventoryItems(){

        System.out.println("Checking Inventory items from database");


        InventoryFullData[] inventoryFullDataArray = webClientBuilder.build().get()
                //.uri("http://localhost:8082/api/inventory/items")
                .uri("http://inventory-service/api/inventory/items")
                .retrieve()
                .bodyToMono(InventoryFullData[].class)
                .block();

        if (inventoryFullDataArray != null) {
            for (InventoryFullData item : inventoryFullDataArray) {
                System.out.println("ID: " + item.getId() +
                        ", SKU Code: " + item.getSkuCode() +
                        ", Quantity: " + item.getQuantity());
            }
        } else {
            System.out.println("No inventory data found.");
        }




    }

    public void placeOrder(OrderRequest orderRequest){


        System.out.println("Inside the placeOrder method");


        for (OrderLineItemDto item : orderRequest.getOrderLineItemDtoList()) {
            System.out.println("SKU Code: " + item.getSkuCode());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("-----------------------------");
        }


        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems =
                 orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemList(orderLineItems);



        //Note : by default 'webClient' will invoke an endpoint in 'async' mode.
        // We need to make it explicitly 'sync' mode and hence being done by using 'block()'

        //curl "http://localhost:8082/api/inventory/list?skuCode=iphone12&skuCode=iphone15"

        List<String> skuCodes=order
                .getOrderLineItemList()
                .stream()
                .map(OrderLineItem::getSkuCode).toList();



        InventoryResponse[] inventoryResponseArray =  webClientBuilder.build().get()
                //.uri("http://localhost:8082/api/inventory/list",
                .uri("http://inventory-service/api/inventory/list",

                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();


        assert inventoryResponseArray != null;
        boolean allProductsInStock = Arrays.
                stream(inventoryResponseArray).
                allMatch(InventoryResponse::isInStock);

        if (Boolean.TRUE.equals(allProductsInStock)){

            System.out.println("[Service] Validated all products in stock. Now saving to database");
            orderRepository.save(order);

            kafkaTemplate.send("notification-topic", new OrderPlacedEvent(order.getOrderNumber()));
            System.out.println("[Service] Order placed successfully !");


        }else {
            throw new IllegalArgumentException(" Product not in stock");
        }



    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {

        OrderLineItem orderLineItems = new OrderLineItem();
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());

        return orderLineItems;


    }

    public void deleteAllOrders() {

        orderRepository.deleteAll();
    }
}
