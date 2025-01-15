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


    //root user is loaded by default with already hashed password in h2db
    //look for data.sql


    //curl -i -X GET http://localhost:8080/users -u root:root


    /*

    on application start, spring automatically inserts this into db

    this is hashed password using online bcrypt website with 'stregnth:10'
    INSERT INTO Auth_User (name, password)
VALUES
    ('root', '$2a$10$YzElQLekXbCSrr.TR3mSaeKwBrV2UJasjYvT6FWUOc4WG2F8wMrmC');


     */
    @RequestMapping("/users")
    public List<AuthUser> getUsers() {
        System.out.println("[controllers] - Fetching list of users");
        return userService.getUsers();

    }


   // curl -i -X POST "http://localhost:8080/register" -H "Content-Type: application/json" -d '{"name":"user1","password":"user1"}' -u root:root
    @PostMapping("/register")
    public AuthUser register(@RequestBody AuthUser authUser){

        return userService.register(authUser);


    }
}
