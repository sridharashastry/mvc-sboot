package com.bring.lab018.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
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


    // curl -i -X GET "http://localhost:8080/countries"   (When spring security is not enabled)
    //  curl -i -X GET "http://localhost:8080/countries" -u admin:admin  (When spring security is enabled)
    @GetMapping("/countries")
    public List<Country> getCountries() {


        return countries;

    }

    //curl -i -X GET "http://localhost:8080/csrftoken" -u admin:admin
    @GetMapping("/csrftoken")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");

    }





    /// Refer file 'curl-for-post.sh' under resources

    @PostMapping("/countries")
    public List<Country> savecountry(@RequestBody Country country) {

        countries.add(country);

        return countries;
    }


}
