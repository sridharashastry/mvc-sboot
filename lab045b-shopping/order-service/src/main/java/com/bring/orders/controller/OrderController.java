package com.bring.orders.controller;


import com.bring.orders.dto.OrderRequest;
import com.bring.orders.models.Order;
import com.bring.orders.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> getOrders(){

        List<Order> Orders = orderService.getAllOrders();
        for (Order item : Orders) {
            System.out.println(item);
        }


       return orderService.getAllOrders();

    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("Attempting to place order: " + orderRequest.toString()); // Log the attempt
        orderService.placeOrder(orderRequest); // Call the target service
        System.out.println("Order placed successfully: " + orderRequest.toString()); // Log success
        return "Order placed successfully: " + orderRequest.toString();
    }






}
