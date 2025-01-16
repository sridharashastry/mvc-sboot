package com.bring.labs.controller;

import com.bring.labs.model.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {


    private List<Country> countries = new ArrayList<>(List.of(new Country(1, "Portugal", "PT", 100),
            new Country(2, "India", "IN", 200)));



    //  curl -i -X GET "http://localhost:8080/countries" -u root:root     /// this is loaded at startup using the 'data.sql' where root: 'hashed password' is stored'

    /*


    curl -i -X GET "http://localhost:8080/countries" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290IiwiaWF0IjoxNzM2OTY5OTEwLCJleHAiOjE3MzY5NzAwMTh9.dr5DArr
eXfWz1xum6G3OQWKW6QggdE9yK2wW4twQiGQ"


     */


    @GetMapping("/countries")
    public List<Country> getCountries() {


        System.out.println("[bringlabs]-Entered CountryController. ");



        return countries;

    }


    // curl -i -X POST "http://localhost:8080/countries" -u root:root -H "Content-Type: application/json" -d '{"id":3,"name":"USA","code":"US","population":300}'
    @PostMapping("/countries")
    public List<Country> savecountry(@RequestBody Country country) {
        countries.add(country);
        return countries;
    }


    //  curl -i -X GET "http://localhost:8080/countries/1" -u root:root
    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable int id) {
        Country country = countries.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
