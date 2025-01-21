package com.bring.labs.service;

import com.bring.labs.model.Country;
import com.bring.labs.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IsolationService {

    private final CountryRepository countryRepository;

    @Autowired
    public IsolationService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateCountry(String countryId, Country updatedCountry) {
        System.out.println("Starting updateCountry for ID: " + countryId);

        try {
            Optional<Country> existingCountry = countryRepository.findById(countryId);

            if (existingCountry.isPresent()) {
                Country country = existingCountry.get();
                country.setCountryName(updatedCountry.getCountryName());
                country.setCountryCode(updatedCountry.getCountryCode());
                country.setPopulation(updatedCountry.getPopulation());

                System.out.println("Updating country in DB...");
                countryRepository.save(country);
            } else {
                System.out.println("Country not found. Creating new country entry...");
                countryRepository.save(updatedCountry);
            }

            System.out.println("Sleeping for 5 seconds to simulate delay...");
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted!");
        }

        System.out.println("updateCountry completed for ID: " + countryId);
    }

    public void getCountryById(String countryId) {
        System.out.println("Fetching country with ID: " + countryId);

        Optional<Country> country = countryRepository.findById(countryId);

        if (country.isPresent()) {
            System.out.println("Country found: " + country.get());
        } else {
            System.out.println("Country with ID " + countryId + " not found.");
        }
    }
}

