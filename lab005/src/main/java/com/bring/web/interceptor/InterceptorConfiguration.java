package com.bring.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {


        /*
    This is a simple interceptor class that intercepts all incoming and outgoing request/responses
    There are two classes that are needed viz., InterceptorConfiguration and CustomInterceptor
     */



    // Registering the custom interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/**") // Apply interceptor to all paths (you can customize this)
                .excludePathPatterns("/login", "/static/**"); // Exclude specific paths (e.g., login or static resources)
    }
}
