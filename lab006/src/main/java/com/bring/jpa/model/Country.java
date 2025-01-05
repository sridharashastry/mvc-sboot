package com.bring.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Country {

    @Id
    private String countryCode;
    private String countryName;


    //this constructor is must
    public Country() {

    }
}

/*
The Data and AllArgsConstructor are from lombok
Data gives getters and setters automatically making the code clean
AllArgsConstructor gives all the constructors automatically, again making the code clean

 */
