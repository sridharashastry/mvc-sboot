package com.bring.labs.controller;


import com.bring.labs.model.AuthUser;
import com.bring.labs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //this endpoint also uses same pattern

    @RequestMapping("/users")
    public List<AuthUser> getUsers() {
        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


        return userService.getUsers();

    }


   // curl -i -X POST "http://localhost:8080/register" -H "Content-Type: application/json" -d '{"name":"user1","password":"user1"}' -u root:root
    @PostMapping("/register")
    public AuthUser register(@RequestBody AuthUser authUser){

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");

        return userService.register(authUser);


    }


    // curl -i -X POST "http://localhost:8080/login" -H "Content-Type: application/json" -d '{"name":"root","password":"root"}'
    @PostMapping("/login")
    public String login(@RequestBody AuthUser authUser){

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


        System.out.println("Incoming user : "+authUser);
        return userService.verify(authUser);


    }


}
