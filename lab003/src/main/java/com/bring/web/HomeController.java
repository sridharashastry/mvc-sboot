package com.bring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greetWorld(){

        System.out.println("Sambar Vada");

        return "Welcome to Spring Boot. Remember we are sending 'just' the data and not the layout";
    }

    /*
    There are two aspects our application should deal with
    1. Layout (html etc) 2. Data (dropdowns, default text etc)
    Previous to SpringBoot, when we had MVC, we were sending the layout as well
    With MSA in place, we have separated the controllers to return just the data and not the layout
    The layout can be controlled or pictured using the React JS or some FE tech stack
     */


}
