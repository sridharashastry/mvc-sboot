package com.bring.labs.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.ConnectException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException | JwtException ex) {
            System.out.println("\n [BringLabs] Catching AuthenticationException or JwtException ");
            handleAuthenticationException(response, ex);
        } catch (Exception ex) {
            handleGenericException(response, ex);
        }
    }

    private void handleAuthenticationException(HttpServletResponse response, Exception ex) throws IOException {

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        String errorMessage = "Authentication failed: " + ex.getMessage();
        System.out.println("\n [BringLabs] Error message :"+errorMessage);
        response.getWriter().write("{\"error\":\"" + errorMessage + "\"}");
    }

    private void handleGenericException(HttpServletResponse response, Exception ex) throws IOException {

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json");
        String errorMessage = "An error occurred: " + ex.getMessage();
        response.getWriter().write("{\"error\":\"" + errorMessage + "\"}");
    }
}
