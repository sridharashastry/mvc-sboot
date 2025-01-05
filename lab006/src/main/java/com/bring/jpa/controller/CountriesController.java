package com.bring.jpa.controller;


import com.bring.jpa.model.Country;

import com.bring.jpa.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountriesController {

    @Autowired
    CountryService countryService;




    ////////////////////CREATE///////////////////////////////////

    //Below curl will create records in database using http 'post' method
    /*

    curl -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "US",   "countryName": "United States" }'
    curl -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "IN",   "countryName": "India" }'
    curl -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "PT",   "countryName": "Portugal" }'


     */




    @PostMapping("/countries")
    public String addCountry(@RequestBody Country country) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String countryJson = objectMapper.writeValueAsString(country);

        System.out.println("[Controller] - Adding country : " + countryJson);

        return countryService.addCountry(country);
    }


    ////////////////////READ-ALL///////////////////////////////////

    //By Default if we don't mention explicit http method it is considered as 'get'

    //curl -X GET http://localhost:8080/countries
    //curl -i -X GET http://localhost:8080/countries   (use this to get http response headers)


    //if we use annotation @RequestMapping, it is by default a 'get'
    @CrossOrigin(origins = "http://localhost:5173")  // Allow CORS for this endpoint
    @RequestMapping("/countries")
    public String getCountries() throws Exception {

        System.out.println("[Controller] - Fetching list of countries");

        List<Country> countries = countryService.getCountries();

        ObjectMapper objectMapper = new ObjectMapper();
        String countriesJson = objectMapper.writeValueAsString(countries);

        System.out.println("[Controller] - Countries: " + countriesJson);

        return countriesJson;
    }

    ////////////////////READ-ALL-WITH-RESPONSE-HEADERS///////////////////////////////////


    //curl -i -X GET http://localhost:8080/countrieswithheaders   (use this to get http response headers. By default Spring does not send any http headers)

    //Important: if we want http headers to be explicitely controlled and sent back to caller, we need to use ResponseEntity
    @RequestMapping("/countrieswithheaders")
    public ResponseEntity<List<Country>> getCountrieswithheaders() {
        System.out.println("[Controller] - Fetching list of countries");
        List<Country> countries = countryService.getCountries();
        return ResponseEntity.ok()
                .header("conversationid", "1111")
                .body(countries);
    }

    ////////////////////READ-SINGLE///////////////////////////////////


    //curl -X GET http://localhost:8080/countries/pt
    //We can use annotation @GetMapping if we want explicitely define a get.
    @GetMapping("/countries/{countryCode}")
    public Country getCountryById(@PathVariable  String countryCode){

        System.out.println("[Controller] - Fetching country by code: "+countryCode);

        return countryService.getCountryById(countryCode);
    }





    ////////////////////UPDATE///////////////////////////////////



    //curl -X PUT http://localhost:8080/countries/US -H "Content-Type: application/json" -d '{ "countryName": "United States of America" }'
    @PutMapping("/countries/{countryCode}")
    public ResponseEntity<String> updateCountryById(@PathVariable String countryCode, @RequestBody Country updatedCountry) {
        System.out.println("[Controller] - Updating country by code: " + countryCode);
        return countryService.updateCountryById(countryCode, updatedCountry);
    }





    ////////////////////DELETE///////////////////////////////////

    // curl -X DELETE http://localhost:8080/countries/pt


    @DeleteMapping("/countries/{countryCode}")
    public ResponseEntity<String> deleteCountryById(@PathVariable String countryCode) {
        System.out.println("[Controller] - Deleting country by code: " + countryCode);
        return countryService.deleteCountryById(countryCode);
    }



}
