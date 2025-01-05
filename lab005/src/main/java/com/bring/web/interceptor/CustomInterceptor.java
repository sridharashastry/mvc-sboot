package com.bring.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CustomInterceptor implements HandlerInterceptor {

    /*
    This is a simple interceptor class that intercepts all incoming and outgoing request/responses
    There are two classes that are needed viz., InterceptorConfiguration and CustomInterceptor
     */





    // Pre-handle method to perform any action before the request reaches the controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("[Interceptor] ---------------------------------------------------------------------------------------");
        System.out.println("[Interceptor] - Pre-handle method: Request URL -> " + request.getRequestURL());
        // Return true to continue with the request processing
        return true;
    }

    // Post-handle method to perform any action after the controller has processed the request
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        System.out.println("[Interceptor] - Post-handle method: Response status -> " + response.getStatus());
    }

    // After completion method to perform any action after the request has been completely processed
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("[Interceptor] - After-completion method: Request complete.");
    }
}
