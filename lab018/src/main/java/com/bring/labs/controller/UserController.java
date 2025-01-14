package com.bring.labs.controller;


import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {





    //curl -i -X GET http://localhost:8080/users
    @RequestMapping("/users")
    public String getUsers() {
        System.out.println("[controllers] - Fetching list of users");
       return "users";
    }




}
