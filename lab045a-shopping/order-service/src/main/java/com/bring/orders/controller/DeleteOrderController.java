package com.bring.orders.controller;


import com.bring.orders.dto.OrderRequest;
import com.bring.orders.models.Order;
import com.bring.orders.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class DeleteOrderController {


    private final OrderService orderService;




    @DeleteMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteAllOrders(){

        System.out.println("Deleting all order....");
        orderService.deleteAllOrders();

        System.out.println("Orders deleted successfully");

        return "Orders deleted successfully";
    }



}


