package com.bring.labs.service;


import com.bring.labs.model.AuthUser;
import com.bring.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JWTService jwtService;



    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public AuthUser register (AuthUser authUser){


        authUser.setPassword(encoder.encode(authUser.getPassword()));
        return userRepository.save(authUser);

    }

    public List<AuthUser> getUsers(){
        return userRepository.findAll();
    }


    public String verify(AuthUser authUser) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser.getName(), authUser.getPassword()));

        if (authentication.isAuthenticated()){

            System.out.println("User is authenticated");
            return jwtService.generateToken(authUser.getName());

        }else {

            System.out.println("User is not authenticated. However, this is never reached and automiatcally 401 is raised by spring security");
            return "failure";
        }



    }
}