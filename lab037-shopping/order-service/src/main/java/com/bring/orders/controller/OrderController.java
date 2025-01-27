package com.bring.orders.controller;


import com.bring.orders.dto.OrderRequest;
import com.bring.orders.models.Order;
import com.bring.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;






    //   curl -X GET "http://localhost:8081/api/order"



    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> placeOrder(){

        List<Order> Orders = orderService.getAllOrders();
        for (Order item : Orders) {
            System.out.println(item);
        }


       return orderService.getAllOrders();

    }









    /*

    Curl to load the data in mysql.
    Ensure database mysql is started.
    Ensure that the database is available.
    Name used for this microservice is : order_service (case sensitive)




BELOW CURL WILL RESULT IN FAILURE

PS C:\ws> curl -X POST http://localhost:8081/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"iphone12","price":100.5,"quantity":2},{"skuCode":"iphone15","price":200.75,"quantity":1}]}'
{"timestamp":"2025-01-26T18:08:15.497+00:00","status":500,"error":"Internal Server Error","path":"/api/order"}
PS C:\ws>

FOR ERROR CHECK THE LOGS

BELOW CURL WILL RESULT IN SUCCESS


  curl -X POST http://localhost:8081/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"iphone12","price":100.5,"quantity":2} ]}'


order placed successfully
PS C:\ws>



     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> placeOrder(@RequestBody OrderRequest orderRequest){



        //Below call simply makes another webclient call to inventory service and prints the data
        //this is just to simulate another plain get call and capture response.
        //no business logic involved here.
        orderService.checkInventoryItems();


        orderService.placeOrder(orderRequest);


        System.out.println("Order placed successfully :"+orderRequest.toString());

        return orderService.getAllOrders();
    }
}



