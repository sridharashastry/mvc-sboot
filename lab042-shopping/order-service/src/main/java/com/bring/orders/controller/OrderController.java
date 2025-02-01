package com.bring.orders.controller;


import com.bring.orders.dto.OrderRequest;
import com.bring.orders.models.Order;
import com.bring.orders.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //   for Curl refer curl sheet

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> getOrders(){

        List<Order> Orders = orderService.getAllOrders();
        for (Order item : Orders) {
            System.out.println(item);
        }


       return orderService.getAllOrders();

    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







    /*

    Curl to load the data in mysql.
    Ensure database mysql is started.
    Ensure that the database is available.
    Name used for this microservice is : order_service (case sensitive)



//important : notice the port is changed from 8081 (previously) to 8080. This is because of the Spring Gateway is started on 8080 and rest all are on dynamic ports.

//8080 is for the spring cloud gateway




BELOW CURL WILL RESULT IN SUCCESS


      curl -X POST http://localhost:8080/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"iphone12","price":100.5,"quantity":2} ]}'



    order placed successfully
    PS C:\ws>


BELOW CURL WILL RESULT IN FAILURE

          curl -X POST http://localhost:8080/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"iphone12","price":100.5,"quantity":2},{"skuCode":"iphone15","price":200.75,"quantity":1}]}'



        {"timestamp":"2025-01-26T18:08:15.497+00:00","status":500,"error":"Internal Server Error","path":"/api/order"}
        PS C:\ws>

        FOR ERROR CHECK THE LOGS




     */


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "placeOrderFallBack")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("Attempting to place order: " + orderRequest.toString()); // Log the attempt
        orderService.placeOrder(orderRequest); // Call the target service
        System.out.println("Order placed successfully: " + orderRequest.toString()); // Log success
        return "Order placed successfully: " + orderRequest.toString();
    }

    public String placeOrderFallBack(OrderRequest orderRequest, RuntimeException runtimeException) {
        System.out.println("Fallback triggered! Target system is down. Exception: " + runtimeException.getMessage()); // Log fallback
        return "Oops! Target System went down! Try Again!";
    }





}


