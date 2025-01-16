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



    //  curl -i -X GET "http://localhost:8080/countries" -u root:root     /// Given the application is now JWT enabled, this will not work.

    /*


Step 1 : Fetch Token
    curl -i -X POST "http://localhost:8080/login" -H "Content-Type: application/json" -d '{"name":"root","password":"root"}'
Step 2 : Use the token and invoke second api
    curl -i -X GET "http://localhost:8080/countries" -H "Authorization: Bearer xxxxxxxxxxtoken-fetchedxxxxxxxxx"


     */


    @GetMapping("/countries")
    public List<Country> getCountries() {


        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");



        return countries;

    }


    // curl -i -X POST "http://localhost:8080/countries"  -H "Authorization: Bearer xxxxxxxxxxx" -H "Content-Type: application/json" -d '{"id":3,"name":"USA","code":"US","population":300}'
    @PostMapping("/countries")
    public List<Country> savecountry(@RequestBody Country country) {

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


        countries.add(country);
        return countries;
    }


    //  curl -i -X GET "http://localhost:8080/countries/1" -H "Authorization: Bearer xxxxxxxxxxx"
    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable int id) {

        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");


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
