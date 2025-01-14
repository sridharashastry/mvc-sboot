package com.bring.lab018;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Lab000 {


    //curl http://localhost:8080
    @RequestMapping("/")
    public String greetTheWorld(){

        System.out.println("Sending greetings");

        return "Hello World";
    }
}
