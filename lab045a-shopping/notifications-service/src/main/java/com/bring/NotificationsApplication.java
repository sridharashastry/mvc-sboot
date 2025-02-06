package com.bring;


import com.bring.events.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
@Slf4j
public class NotificationsApplication {

    public static void main (String[] args){

        SpringApplication.run(NotificationsApplication.class, args);
    }

    @KafkaListener (topics = "notification-topic")
    public void handleNotifications(OrderPlacedEvent orderPlacedEvent){


        log.info("Received Notification for order - {} ",orderPlacedEvent.getOrderNumber());

    }
}
