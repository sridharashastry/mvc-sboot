package com.bring.web.controllers;

import com.bring.web.models.Country;
import com.bring.web.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountriesController {

    @Autowired
    CountryService countryService;

    //By Default if we don't mention explicit http method it is considered as 'get'

    //curl -X GET http://localhost:8080/countries
    @RequestMapping("/countries")
    public List<Country> getCountries(){

        System.out.println("Fetching list of countries");

        return countryService.getCountries();


    }

    @RequestMapping("/countries/{countryCode}")
    public Country getCountryById(@PathVariable  String countryCode){

        System.out.println("Fetching country by code: "+countryCode);

        return countryService.getCountryById(countryCode);
    }

    //Below we are explicitely making our http method a post

    @PostMapping("/countries")
    public String addCountry(@RequestBody Country country){


        System.out.println("Adding country : "+country);

        return countryService.addCountry(country);
    }


}
