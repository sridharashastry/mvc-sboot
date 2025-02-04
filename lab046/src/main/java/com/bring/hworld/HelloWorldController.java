package com.bring.hworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

@GetMapping("/hello")
public String sayHello() {

    System.out.println("Saying Hello to world ... ");
    return "hello world";
}
}
