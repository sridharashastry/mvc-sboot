package com.bring.orders.service;


import com.bring.orders.dto.OrderLineItemDto;
import com.bring.orders.dto.OrderRequest;
import com.bring.orders.models.Order;
import com.bring.orders.models.OrderLineItem;
import com.bring.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;





    public void placeOrder(OrderRequest orderRequest){

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems =
                 orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemList(orderLineItems);

        orderRepository.save(order);

    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {

        OrderLineItem orderLineItems = new OrderLineItem();
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());

        return orderLineItems;


    }
}
