package com.bring.labs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int countryId;
    private String countryCode;
    private String countryName;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] countryImage;

    private int population;



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
