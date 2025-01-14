package com.bring.labs.controller;


import com.bring.labs.model.Country;
import com.bring.labs.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UpdateCountryController {

    @Autowired
    CountryService countryService;



    ////////////////////UPDATE///////////////////////////////////





    //curl -i -X PUT http://localhost:8080/countries/1 -H "Content-Type: application/json" -d '{ "countryCode":"US","countryName": "USA" }'
    @PutMapping("/countries/{countryId}")
    public ResponseEntity<String> updateCountryById(@PathVariable String countryId, @RequestBody Country updatedCountry) {
        System.out.println("[controllers] - Updating country by countryId: " + countryId +"and incoming payload "+updatedCountry.toString());


        try {
            countryService.updateCountryById(countryId, updatedCountry);
            return ResponseEntity.ok("Country " + countryId + " updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
