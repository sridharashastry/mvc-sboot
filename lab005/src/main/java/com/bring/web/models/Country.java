package com.bring.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Country {

    private String countryCode;
    private String countryName;



}

/*
The Data and AllArgsConstructor are from lombok
Data gives getters and setters automatically making the code clean
AllArgsConstructor gives all the constructors automatically, again making the code clean

 */
