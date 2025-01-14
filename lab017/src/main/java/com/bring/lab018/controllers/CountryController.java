package com.bring.lab018.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {


    private List<Country> countries = new ArrayList<>(List.of(new Country(1, "Portugal", "PT", 100),
            new Country(2, "India", "IN", 200)));



    //  curl -i -X GET "http://localhost:8080/countries" -u admin:admin
    //  Provision of Credentials now becomes must as now it is controlled by 'configuration'
    @GetMapping("/countries")
    public List<Country> getCountries() {


        return countries;

    }




    /// Refer file 'curl-for-post.sh' under resources

    @PostMapping("/countries")
    public List<Country> savecountry(@RequestBody Country country) {

        countries.add(country);

        return countries;
    }


}
