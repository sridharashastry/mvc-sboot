package com.bring.labs.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    //private String secretKey="cf781A";   //having a key like this will fail
    private String secretKey = "";

    public JWTService(){


        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }



    public String generateToken(String username) {


        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();




        //return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InJvb3QiLCJpYXQiOjE1MTYyMzkwMjJ9.FbUsFDKoxwS8RT1TZI_2OYTbW9Qq_NbSxOWlGTsOVvI";
    }


    private Key getKey(){

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);


        return Keys.hmacShaKeyFor(keyBytes);

    }
}
