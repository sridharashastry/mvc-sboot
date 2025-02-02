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
    @TimeLimiter(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        System.out.println("Attempting to place order: " + orderRequest.toString()); // Log the attempt


        return CompletableFuture.supplyAsync(() ->orderService.placeOrder(orderRequest)); // Call the target service


        /////THIS IS NOT WORKING AS EXPECTED. LOGS ARE PLACED BELOW
        ///FURTHER QUERYING IS NEEDED.


    }

    public CompletableFuture<String> placeOrderFallBack(OrderRequest orderRequest, RuntimeException runtimeException) {
        System.out.println("Fallback triggered! Target system is down. Exception: " + runtimeException.getMessage()); // Log fallback
        return CompletableFuture.supplyAsync(()-> "Oops! Target System went down! Try Again!");
    }





}

/*
2025-02-02T20:07:02.554Z  INFO 9752 --- [orders-service] [foReplicator-%d] com.netflix.discovery.DiscoveryClient    : DiscoveryClient_ORDERS-SERVICE/orders-service:680: registering service...
2025-02-02T20:07:02.610Z  INFO 9752 --- [orders-service] [foReplicator-%d] com.netflix.discovery.DiscoveryClient    : DiscoveryClient_ORDERS-SERVICE/orders-service:680 - registration status: 204
2025-02-02T20:07:03.700Z  INFO 9752 --- [orders-service] [           main] o.s.cloud.commons.util.InetUtils         : Cannot determine local hostname
2025-02-02T20:07:04.876Z  INFO 9752 --- [orders-service] [           main] o.s.cloud.commons.util.InetUtils         : Cannot determine local hostname
2025-02-02T20:07:04.906Z  INFO 9752 --- [orders-service] [           main] com.bring.orders.OrdersApplication       : Started OrdersApplication in 17.893 seconds (process running for 18.617)
2025-02-02T20:07:47.486Z  INFO 9752 --- [orders-service] [o-auto-1-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2025-02-02T20:07:47.487Z  INFO 9752 --- [orders-service] [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2025-02-02T20:07:47.488Z  INFO 9752 --- [orders-service] [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
Attempting to place order: OrderRequest(orderLineItemDtoList=[OrderLineItemDto(id=null, skuCode=iphone12, price=100.5, quantity=2)])
Inside the placeOrder method
SKU Code: iphone12
Price: 100.5
Quantity: 2
-----------------------------
2025-02-02T20:07:50.611Z ERROR 9752 --- [orders-service] [o-auto-1-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] threw exception

java.util.concurrent.TimeoutException: TimeLimiter 'inventory' recorded a timeout exception.
        at io.github.resilience4j.timelimiter.TimeLimiter.createdTimeoutExceptionWithName(TimeLimiter.java:225) ~[resilience4j-timelimiter-2.2.0.jar!/:2.2.0]
        at io.github.resilience4j.timelimiter.internal.TimeLimiterImpl$Timeout.lambda$of$0(TimeLimiterImpl.java:185) ~[resilience4j-timelimiter-2.2.0.jar!/:2.2.0]
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539) ~[na:na]
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
        at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]

2025-02-02T20:07:50.620Z ERROR 9752 --- [orders-service] [o-auto-1-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: java.util.concurrent.TimeoutException: T
imeLimiter 'inventory' recorded a timeout exception.] with root cause

java.util.concurrent.TimeoutException: TimeLimiter 'inventory' recorded a timeout exception.
        at io.github.resilience4j.timelimiter.TimeLimiter.createdTimeoutExceptionWithName(TimeLimiter.java:225) ~[resilience4j-timelimiter-2.2.0.jar!/:2.2.0]
        at io.github.resilience4j.timelimiter.internal.TimeLimiterImpl$Timeout.lambda$of$0(TimeLimiterImpl.java:185) ~[resilience4j-timelimiter-2.2.0.jar!/:2.2.0]
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539) ~[na:na]
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
        at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]

[Service] Validated all products in stock. Now saving to database
2025-02-02T20:07:58.405Z DEBUG 9752 --- [orders-service] [onPool-worker-1] org.hibernate.SQL                        : select next_val as id_val from t_order_seq for update
2025-02-02T20:07:58.408Z DEBUG 9752 --- [orders-service] [onPool-worker-1] org.hibernate.SQL                        : update t_order_seq set next_val= ? where next_val=?
2025-02-02T20:07:58.439Z DEBUG 9752 --- [orders-service] [onPool-worker-1] org.hibernate.SQL                        : select next_val as id_val from t_order_items_seq for update
2025-02-02T20:07:58.440Z DEBUG 9752 --- [orders-service] [onPool-worker-1] org.hibernate.SQL                        : update t_order_items_seq set next_val= ? where next_val=?
[Service] Order placed successfully !
2025-02-02T20:07:58.466Z DEBUG 9752 --- [orders-service] [onPool-worker-1] org.hibernate.SQL                        : insert into t_order (order_number,id) values (?,?)
2025-02-02T20:07:58.472Z DEBUG 9752 --- [orders-service] [onPool-worker-1] org.hibernate.SQL                        : insert into t_order_items (price,quantity,sku_code,id) values (?,?,?,?)
2025-02-02T20:07:58.476Z DEBUG 9752 --- [orders-service] [onPool-worker-1] org.hibernate.SQL                        : insert into t_order_order_line_item_list (order_id,order_line_item_list_id) values (?,?)

 */

