package com.bring.web.services;

import com.bring.web.models.Country;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class CountryService {

    List<Country> countries =Arrays.asList(
            new Country("pt", "Portugal"),
            new Country("fr", "France"),
            new Country("es", "Spain"),
            new Country("de", "Germany"),
            new Country("it", "Italy"));

    public List<Country> getCountries(){

        System.out.println("Returning list of countries");
        return countries;

    }

    public Country getCountryById( String countryCode){

        System.out.println("Returning country by code: "+countryCode);

        /*

        This returns exception
        return countries.stream()
                .filter(p -> p.getCountryCode().equalsIgnoreCase(countryCode))  // Use equalsIgnoreCase to ignore case
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found: " + countryCode));  // Throw exception if not found


         */

        return countries.stream()
                .filter(p -> p.getCountryCode().equalsIgnoreCase(countryCode))  // Use equalsIgnoreCase to ignore case
                .findFirst()
                .orElse(new Country(countryCode,"Not Available"));  // Return null if not found
    }


    public String addCountry(Country country){

        System.out.println("Adding country : "+country);


        return "Country Added";
    }
}
