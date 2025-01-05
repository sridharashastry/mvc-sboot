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

        System.out.println("[Service] - Returning list of countries");
        return countries;

    }

    public Country getCountryById( String countryCode){

        System.out.println("[Service] - Returning country by code: "+countryCode);

        return countries.stream()
                .filter(p -> p.getCountryCode().equalsIgnoreCase(countryCode))  // Use equalsIgnoreCase to ignore case
                .findFirst()
                .orElse(new Country(countryCode,"Not Available"));  // Return null if not found
    }


    public String addCountry(Country country){

        System.out.println("[Service] - Adding country : "+country);

        //here nothing techcnially happenning interms of adding but returning a dummy response

        return "Country Added";
    }


    public String deleteCountryById(String countryCode) {
        System.out.println("[Service] - Deleting country with code: " + countryCode);
        // Dummy response for deletion
        return "Country "+countryCode+" Deleted";
    }

    public String updateCountryById(String countryCode, Country updatedCountry) {
        System.out.println("[Service] - Updating country with code: " + countryCode + " to: " + updatedCountry);
        // Dummy response for update
        return "Country "+countryCode+" Updated";
    }

}
