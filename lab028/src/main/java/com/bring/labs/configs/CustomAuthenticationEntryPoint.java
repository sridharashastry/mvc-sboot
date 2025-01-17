package com.bring.labs.configs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, IOException {

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");



        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        String errorMessage = "JWT authentication failed: " + authException.getMessage();
        response.getWriter().write("{\"error\":\"" + errorMessage + "\"}");
    }
}