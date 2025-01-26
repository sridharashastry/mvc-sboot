package com.bring.orders.service;


import com.bring.orders.dto.InventoryResponse;
import com.bring.orders.dto.OrderLineItemDto;
import com.bring.orders.dto.OrderRequest;
import com.bring.orders.models.Order;
import com.bring.orders.models.OrderLineItem;
import com.bring.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.StyledEditorKit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    //@Autowired
    private final OrderRepository orderRepository;
    private final WebClient webClient;



    public List<Order> getAllOrders(){
       return orderRepository.findAll();
    }


    public void placeOrder(OrderRequest orderRequest){

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


        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory/list",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();


        assert inventoryResponseArray != null;
        boolean allProductsInStock = Arrays.
                stream(inventoryResponseArray).
                allMatch(InventoryResponse::isInStock);

        if (Boolean.TRUE.equals(allProductsInStock)){

            orderRepository.save(order);

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
}
