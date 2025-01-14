package com.bring.labs.controller;


import com.bring.labs.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class DeleteCountryController {

    @Autowired
    CountryService countryService;



    ////////////////////DELETE///////////////////////////////////

    // curl -X DELETE http://localhost:8080/countries/1


    @DeleteMapping("/countries/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable int countryId) {
        System.out.println("[controllers] - Deleting country by countryId: " + countryId);
        String response = countryService.deleteCountryById(countryId);
        return ResponseEntity.ok(response);
    }



}
