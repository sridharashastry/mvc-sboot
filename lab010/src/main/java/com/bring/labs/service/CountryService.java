package com.bring.labs.service;


import com.bring.labs.model.Country;
import com.bring.labs.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class CountryService {


    @Autowired
    CountryRepository countryRepository;

    public List<Country> getCountries(){


        return countryRepository.findAll();

    }


    public Country getCountryById(String countryId) {

        return countryRepository.findById(countryId).orElse(new Country());
    }

    public String addCountry(Country country){

        countryRepository.save(country);
        return "Country "+country+" Saved";
    }



    public void updateCountryById(String countryId, Country updatedCountry) {

        Optional<Country> existingCountry = countryRepository.findById(countryId);

        if (existingCountry.isPresent()) {
            existingCountry.get().setCountryName(updatedCountry.getCountryName());
            existingCountry.get().setCountryCode(updatedCountry.getCountryCode());
            existingCountry.get().setPopulation(updatedCountry.getPopulation());
            countryRepository.save(existingCountry.get());
        } else {
            throw new RuntimeException("Country with countryId " + countryId + " not found");
        }
    }


    public String deleteCountryById(String countryId) {

        if (!countryRepository.existsById(countryId)) {
            return "Country with countryId " + countryId + " not found";
        }

        countryRepository.deleteById(countryId);
        return "Country with countryId" + countryId + " Deleted";
    }

    public Country addCountryWithImage(Country country, MultipartFile image) throws IOException {

        country.setImageName(image.getOriginalFilename());
        country.setImageType(image.getContentType());
        country.setCountryImage(image.getBytes());

        return countryRepository.save(country);

    }

    /*

Lifecycle Differences between update and add


Aspect	        addCountryWithImage	                    updateCountryById
Entity State	New (Transient)	                        Retrieved from the database (Managed)
Save Behavior	Creates a new record (INSERT)	        Updates an existing record (UPDATE)
Tracking	    Not previously tracked by Hibernate	    Already tracked by Hibernate after findById()
ID Handling	    No existing id, creates a new row	    Uses existing id, modifies the same row


If you skip save() after modifying existingCountry.get(),
the changes might still be persisted implicitly,
but calling save() explicitly is a good practice for clarity and control.



     */





}