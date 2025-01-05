package com.bring.jpa.service;


import com.bring.jpa.model.Country;
import com.bring.jpa.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;



@Service
public class CountryService {


    @Autowired
    CountryRepository countryRepository;

    public List<Country> getCountries(){


        List<Country> countries = countryRepository.findAll();
        System.out.println("[Service] - Returning list of countries");
        System.out.println(countries);
        return countries;

    }


    public Country getCountryById( String countryCode){


        Country country = countryRepository.findById(countryCode).orElse(new Country());

        System.out.println("[Service] - Returning country: "+country);

        return country;


    }


    public String addCountry(Country country){

        System.out.println("[Service] - Adding country : "+country);

        countryRepository.save(country);
        return "Country "+country+" Saved";
    }

    public ResponseEntity<String> deleteCountryById(String countryCode) {
        System.out.println("[Service] - Deleting country with code: " + countryCode);

        if (!countryRepository.existsById(countryCode)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Country with code " + countryCode + " not found");
        }

        countryRepository.deleteById(countryCode);
        return ResponseEntity.ok("Country " + countryCode + " Deleted");
    }


    public ResponseEntity<String> updateCountryById(String countryCode, Country updatedCountry) {
        System.out.println("[Service] - Updating country with code: " + countryCode);

        if (!countryRepository.existsById(countryCode)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Country with code " + countryCode + " not found");
        }

        updatedCountry.setCountryCode(countryCode); // Ensure the code matches
        countryRepository.save(updatedCountry);
        return ResponseEntity.ok("Country " + countryCode + " updated successfully");
    }



}
