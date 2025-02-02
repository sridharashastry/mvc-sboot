
package com.bring.discoveryserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        System.out.println("pista singh is here");


        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for Eureka endpoints
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator/**").permitAll() // Allow access to actuator endpoints
                        .requestMatchers("/eureka/**").hasRole("USER") // Secure Eureka endpoints with USER role
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // Enable HTTP Basic authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Create an in-memory user with username 'eureka', password 'eureka', and role 'USER'
        UserDetails user = User.withUsername("eureka")
                .password("eureka")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use NoOpPasswordEncoder for simplicity (not recommended for production)
        return NoOpPasswordEncoder.getInstance();
    }
}
