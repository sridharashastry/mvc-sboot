/*
package com.bring.labs.controllers;


import com.bring.labs.model.Country;

import com.bring.labs.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CountriesController {

    @Autowired
    CountryService countryService;




    ////////////////////CREATE///////////////////////////////////

    //Data is automatically loaded using the data.sql that is part of resources.
    //there is a spring configuration as well that delays the insert queries.

    */
/*
    Below are the curl just in case if we want to load manually.

    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "US",   "countryName": "United States" }'
    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "IN",   "countryName": "India" }'
    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "PT",   "countryName": "Portugal" }'

     *//*

    @PostMapping("/countries")
    public ResponseEntity<String> addCountry(@RequestBody Country country) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String countryJson = objectMapper.writeValueAsString(country);

        System.out.println("[controllers] - Adding country : " + countryJson);

        String response = countryService.addCountry(country);
        return ResponseEntity.ok()
                .body(response);
    }






    ////////////////////READ-ALL///////////////////////////////////


    //curl -i -X GET http://localhost:8080/countries   (use this to get http response headers. By default Spring does not send any http headers)

    //Important: if we want http headers to be explicitly controlled and sent back to caller, we need to use ResponseEntity
    @RequestMapping("/countries")
    public ResponseEntity<List<Country>> getCountries() {
        System.out.println("[controllers] - Fetching list of countries");
        List<Country> countries = countryService.getCountries();
        return ResponseEntity.ok()
                .header("conversationid", "1111")
                .body(countries);
    }





    ////////////////////READ-SINGLE///////////////////////////////////


    //curl -i -X GET http://localhost:8080/countries/1
    //We can use annotation @GetMapping if we want explicitly define a get.
    @GetMapping("/countries/{countryId}")
    public ResponseEntity<Country> getCountryById(@PathVariable String countryId) {

        System.out.println("[controllers] - Fetching country by countryId: " + countryId);

        Country country = countryService.getCountryById(countryId);
        return ResponseEntity.ok(country);
    }




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


    ////////////////////DELETE///////////////////////////////////

    // curl -X DELETE http://localhost:8080/countries/1


    @DeleteMapping("/countries/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable String countryId) {
        System.out.println("[controllers] - Deleting country by countryId: " + countryId);
        String response = countryService.deleteCountryById(countryId);
        return ResponseEntity.ok(response);
    }



}
*/
