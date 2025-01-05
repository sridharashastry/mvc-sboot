package com.bring.web.controllers;

import com.bring.web.models.Country;
import com.bring.web.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountriesController {

    @Autowired
    CountryService countryService;

    //By Default if we don't mention explicit http method it is considered as 'get'

    //curl -X GET http://localhost:8080/countries
    //curl -i -X GET http://localhost:8080/countries   (use this to get http response headers)


    //if we use annotation @RequestMapping, it is by default a 'get'
    @RequestMapping("/countries")
    public List<Country> getCountries(){

        System.out.println("[Controller] - Fetching list of countries");

        return countryService.getCountries();


    }


    //curl -i -X GET http://localhost:8080/countrieswithheaders   (use this to get http response headers)

    @RequestMapping("/countrieswithheaders")
    public ResponseEntity<List<Country>> getCountrieswithheaders() {
        System.out.println("[Controller] - Fetching list of countries");
        List<Country> countries = countryService.getCountries();
        return ResponseEntity.ok()
                .header("conversationid", "1111")
                .body(countries);
    }


    //curl -X GET http://localhost:8080/countries/pt
    //We can use annotation @GetMapping if we want explicitely define a get.
    @GetMapping("/countries/{countryCode}")
    public Country getCountryById(@PathVariable  String countryCode){

        System.out.println("[Controller] - Fetching country by code: "+countryCode);

        return countryService.getCountryById(countryCode);
    }



    //Below we are explicitely making our http method a post
    // curl -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "US",   "countryName": "United States" }'
    @PostMapping("/countries")
    public String addCountry(@RequestBody Country country){

        System.out.println("[Controller] - Adding country : "+country);

        return countryService.addCountry(country);
    }


    /*
    curl -X DELETE http://localhost:8080/countries/pt

curl -X PUT -H "Content-Type: application/json" -d '{"name":"Portugal Updated","population":10500000}' http://localhost:8080/countries/pt

     */

    @DeleteMapping("/countries/{countryCode}")
    public String deleteCountryById(@PathVariable String countryCode) {
        System.out.println("[Controller] - Deleting country by code: " + countryCode);
        return countryService.deleteCountryById(countryCode);
    }

    @PutMapping("/countries/{countryCode}")
    public String updateCountryById(@PathVariable String countryCode, @RequestBody Country updatedCountry) {
        System.out.println("[Controller] - Updating country by code: " + countryCode);
        return countryService.updateCountryById(countryCode, updatedCountry);
    }


}
