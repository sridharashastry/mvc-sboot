package com.bring.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int countryId;
    private String countryCode;
    private String countryName;



    public Country() {

        //this constructor is must
        //if we are not explicitly defining this, then use the annotation "@NoArgsConstructor"

    }
}

/*

Annotations Data and AllArgsConstructor are from lombok
Data gives getters and setters automatically making the code clean
AllArgsConstructor gives all the constructors automatically, again making the code clean

 */
