package com.bring.labs.model;


import lombok.Getter;

@Getter
public class Country {


    private final int id;
    private final String name;
    private final String code;
    private final int population;


    //constructor

    public Country(int id, String name, String code, int population) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.population = population;
    }


    //to string
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", population=" + population +
                '}';
    }
}
