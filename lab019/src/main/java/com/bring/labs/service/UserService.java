package com.bring.labs.service;


import com.bring.labs.model.AuthUser;
import com.bring.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public AuthUser register (AuthUser authUser){


        authUser.setPassword(encoder.encode(authUser.getPassword()));
        return userRepository.save(authUser);

    }

    public List<AuthUser> getUsers(){
        return userRepository.findAll();
    }






}
