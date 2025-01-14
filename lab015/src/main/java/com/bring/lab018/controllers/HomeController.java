package com.bring.lab018.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {


    //curl -i -X GET "http://localhost:8080/"
    @RequestMapping("/")
    public String greetTheWorld(HttpServletRequest request){

        System.out.println("Sending greetings!!");

        return "Hello World. Your session id :"+request.getSession().getId();
    }
}
