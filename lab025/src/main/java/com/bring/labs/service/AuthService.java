package com.bring.labs.service;

import com.bring.labs.model.UserPrincipal;
import com.bring.labs.repository.UserRepository;
import com.bring.labs.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


        System.out.println("Incoming username: " + username);
        AuthUser u = userRepository.findByName(username);

        if (u == null) {
            System.out.println("User not found in database");
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("User found in database: " + u.toString());
        System.out.println("User ID: " + u.getId());
        System.out.println("User Name: " + u.getName());
        System.out.println("User Password: " + u.getPassword());

        return new UserPrincipal(u);
    }
}
