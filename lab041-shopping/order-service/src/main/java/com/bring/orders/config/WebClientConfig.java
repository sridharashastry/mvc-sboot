package com.bring.orders.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Note when we create a Bean using below annotation, spring will automatically create the bean with the name of method
    // Example the bean name would be 'webClient'

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){

        return WebClient.builder();
    }


}
