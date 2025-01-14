package com.bring.labs.controller;


import com.bring.labs.model.Country;
import com.bring.labs.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SearchCountryController {

    @Autowired
    CountryService countryService;



    ////////////////////SEARCH///////////////////////////////////

    // curl -i -X GET "http://localhost:8080/countries/search?keyword=IN" -H "Accept: application/json"   Note: keyword is 'IN'  gets india

    // curl -i -X GET "http://localhost:8080/countries/search?keyword=al" -H "Accept: application/json"   Note: keyword is 'al'  gets portugal

    // curl -i -X GET "http://localhost:8080/countries/search?keyword=0" -H "Accept: application/json"    Note: keyword is '0' - gets both the records

    @GetMapping("/countries/search")
    public ResponseEntity<List<Country>> searchCountry(@RequestParam String keyword) {
        System.out.println("[controllers] - Search country by keyword: " + keyword);

        Specification<Country> spec = (root, query, criteriaBuilder) -> {
            String likeKeyword = "%" + keyword.toLowerCase() + "%";



            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("countryName")), likeKeyword),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("countryCode")), likeKeyword)
            );


        };

        List<Country> countries = countryService.findAll(spec);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }






}
