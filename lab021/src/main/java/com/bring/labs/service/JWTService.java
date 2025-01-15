package com.bring.labs.service;


import org.springframework.stereotype.Service;

@Service
public class JWTService {


    public String generateToken() {

        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InJvb3QiLCJpYXQiOjE1MTYyMzkwMjJ9.FbUsFDKoxwS8RT1TZI_2OYTbW9Qq_NbSxOWlGTsOVvI";
    }
}
