package com.bring.orders.controller;


import com.bring.orders.dto.OrderRequest;
import com.bring.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    /*

    Curl to load the data in mysql. Ensure database mysql is started


curl -X POST http://localhost:8081/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"SKU123","price":100.5,"quantity":2},{"skuCode":"SKU124","price":200.75,"quantity":1}]}'

curl -X POST http://localhost:8081/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"SKU125","price":300.5,"quantity":21},{"skuCode":"SKU126","price":400.75,"quantity":11}]}'



     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "order placed successfully";
    }
}


//add create database in schema.sql

