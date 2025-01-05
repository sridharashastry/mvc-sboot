package com.bring.jpa.controller;


import com.bring.jpa.model.Country;
import com.bring.jpa.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CreateCountryController {

    @Autowired
    CountryService countryService;


    //Data is automatically loaded using the data.sql that is part of resources.
    //there is a spring configuration as well that delays the insert queries.

    /*
    Below are the curl just in case if we want to load manually.

    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "US",   "countryName": "United States" }'
    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "IN",   "countryName": "India" }'
    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "PT",   "countryName": "Portugal" }'

     */
    @PostMapping("/countries")
    public ResponseEntity<String> addCountry(@RequestBody Country country) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String countryJson = objectMapper.writeValueAsString(country);

        System.out.println("[Controller] - Adding country : " + countryJson);

        String response = countryService.addCountry(country);
        return ResponseEntity.ok()
                .body(response);
    }







}
