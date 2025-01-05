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
public class DeleteCountryController {

    @Autowired
    CountryService countryService;



    ////////////////////DELETE///////////////////////////////////

    // curl -X DELETE http://localhost:8080/countries/1


    @DeleteMapping("/countries/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable int countryId) {
        System.out.println("[Controller] - Deleting country by countryId: " + countryId);
        String response = countryService.deleteCountryById(countryId);
        return ResponseEntity.ok(response);
    }



}
