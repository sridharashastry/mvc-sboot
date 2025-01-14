package com.bring.lab018.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    /*
@Configuration: Marks the class as a source of bean definitions for the application context, allowing it to configure the Spring context.

@EnableWebSecurity: Enables Spring Security’s web security support, allowing configuration of security settings for web requests.


Note :
1. By mentioning above, we are telling Spring, not to go with 'default' security flow, but go with what we are mentioning. In simple terms, we are overriding.
2. If we Disable @EnableWebSecurity and @Bean and keep the method simple by default all the security will will be ENABLED. Meaning, we will get login form.
3. Eg. like below


    //@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.build();
    }

4. However, when we enable @EnableWebSecurity and @Bean and keep the above method simple, then by default Spring will DISABLE security. Because we put annotation but did not do anything explicitely.

5. Below annotation will also be helpful

  @EnableMethodSecurity: Enables method-level security annotations like @PreAuthorize or @Secured, allowing fine-grained access control at the method level.

     */




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http
                //.csrf(customizer -> customizer.disable())    //without lambda
                .csrf(AbstractHttpConfigurer::disable)          //with lambda
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();



    }

}
