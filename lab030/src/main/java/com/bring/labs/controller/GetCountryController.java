package com.bring.labs.controller;


import com.bring.labs.model.Country;
import com.bring.labs.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class GetCountryController {

    @Autowired
    CountryService countryService;





    ////////////////////READ-ALL///////////////////////////////////


    //  curl -i -X GET http://localhost:8080/countries

    @RequestMapping("/countries")
    public ResponseEntity<List<Country>> getCountries() {

        return countryService.getCountries();

    }





    ////////////////////READ-SINGLE///////////////////////////////////


    //curl -i -X GET http://localhost:8080/countries/1
    //We can use annotation @GetMapping if we want explicitly define a get.
    @GetMapping("/countries/{countryId}")
    public ResponseEntity<Object> getCountryById(@PathVariable String countryId) {

        System.out.println("[controllers] - Fetching country by countryId: " + countryId);

        Country country = countryService.getCountryById(countryId);

        // Check if the retrieved country object itself is null
        // or if any of its essential fields (e.g., countryCode, countryName) are null
        if (country == null ||
                (country.getCountryCode() == null && country.getCountryName() == null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country with ID " + countryId + " not found.");
        } else {

           System.out.println("[controllers] - Returning country: " + country);

            return ResponseEntity.ok(country);
        }




    }









}
