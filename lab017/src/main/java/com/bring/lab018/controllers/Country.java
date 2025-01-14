package com.bring.lab018.controllers;




public class Country {

    private int id;
    private String name;
    private String code;
    private int population;


    //constructor

    public Country(int id, String name, String code, int population) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.population = population;
    }


    //getter setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getPopulation() {
        return population;
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
