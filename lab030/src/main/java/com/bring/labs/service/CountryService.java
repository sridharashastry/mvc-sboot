package com.bring.labs.service;


import com.bring.labs.interfaces.UUIDGenerator;
import com.bring.labs.model.Country;
import com.bring.labs.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryService {


    public CountryService(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }
    @Autowired
    CountryRepository countryRepository;

    private final UUIDGenerator uuidGenerator;

    public ResponseEntity<List<Country>> getCountries(){



        List<Country> countries = countryRepository.findAll();
        return ResponseEntity.ok()
                .header("conversationid", uuidGenerator.generateUUID())
                .body(countries);




    }


    public Country getCountryById(String countryId) {

        return countryRepository.findById(countryId).orElse(new Country());
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

}
