package com.bring.labs.service;


import com.bring.labs.model.Country;
import com.bring.labs.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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


    public Country getCountryById(int countryId) {

        return countryRepository.findById(countryId).orElse(new Country());
    }

    public String addCountry(Country country){

        countryRepository.save(country);
        return "Country "+country+" Saved";
    }



    public void updateCountryById(int countryId, Country updatedCountry) {

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


    public String deleteCountryById(int countryId) {

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



    public List<Country> findAll(Specification<Country> spec) {
        return countryRepository.findAll(spec);
    }





}
