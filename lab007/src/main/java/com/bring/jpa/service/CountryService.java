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
import java.util.Optional;


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


    public Country getCountryById( String countryId){


        Country country = countryRepository.findById(countryId).orElse(new Country());

        System.out.println("[Service] - Returning country: "+country);

        return country;


    }


    public String addCountry(Country country){

        System.out.println("[Service] - Adding country : "+country);

        countryRepository.save(country);
        return "Country "+country+" Saved";
    }



    public void updateCountryById(String countryId, Country updatedCountry) {
        System.out.println("[Service] - Updating country using countryId: " + countryId);

        Optional<Country> existingCountry = countryRepository.findById(countryId);

        if (existingCountry.isPresent()) {
            existingCountry.get().setCountryName(updatedCountry.getCountryName());
            existingCountry.get().setCountryCode(updatedCountry.getCountryCode());
            countryRepository.save(existingCountry.get());
        } else {
            throw new RuntimeException("Country with countryId " + countryId + " not found");
        }
    }


    public String deleteCountryById(String countryId) {
        System.out.println("[Service] - Deleting country with code: " + countryId);

        if (!countryRepository.existsById(countryId)) {
            return "Country with countryId " + countryId + " not found";
        }

        countryRepository.deleteById(countryId);
        return "Country with countryId" + countryId + " Deleted";
    }

}
