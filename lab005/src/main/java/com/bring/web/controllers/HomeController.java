package com.bring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    //curl http://localhost:8080/
    @RequestMapping("/")
    public String greetWorld(){

        System.out.println("Sambar Vada");

        return "Welcome to Spring Boot. Remember we are sending 'just' the data and not the layout";
    }

   //curl http://localhost:8080/tickets
    //This is super raw to return a sample json.
    //We will be using proper models in real services.
    @RequestMapping("/tickets")
    public String gettickets() {
        return "{ \"data\":\"comingsoon\"}";
    }


}
