package com.bring.jpa.controller;


import com.bring.jpa.model.Country;
import com.bring.jpa.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@CrossOrigin
@RestController
public class CreateCountryController {

    @Autowired
    CountryService countryService;


    //Data is automatically loaded using the data.sql that is part of resources.
    //there is a spring configuration as well that delays the insert queries.
    //Note the data is loaded 'without images'

    /*
    Below are the curl just in case if we want to load manually.

    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "US",   "countryName": "United States" }'
    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "IN",   "countryName": "India" }'
    curl -i -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d '{   "countryCode": "PT",   "countryName": "Portugal" }'

     */
    @PostMapping("/countries")
    public ResponseEntity<String> addCountry(@RequestBody Country country) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String countryJson = objectMapper.writeValueAsString(country);

        System.out.println("[Controller] - Adding country : " + countryJson);

        String response = countryService.addCountry(country);
        return ResponseEntity.ok()
                .body(response);
    }



   // curl -i -X POST -H "Content-Type: multipart/form-data" -F "country=@C:/ws/sboot/lab010/IN-country-data.json;type=application/json" -F "image=@C:/ws/sboot/lab010/IN-country-image.png" http://localhost:8080/country-with-image

    // curl -i -X POST -H "Content-Type: multipart/form-data" -F "country=@C:/ws/sboot/lab010/PT-country-data.json;type=application/json" -F "image=@C:/ws/sboot/lab010/PT-country-image.png" http://localhost:8080/country-with-image

    //important: ensure two files are available at designated places.
    //one : xx-country-data.json
    //two : xx-country-image.png

    //In the spring boot application itself, there is command line runner configured to load 2 images, automatically
    //ontop of it, if we want, use the curl

    @PostMapping("/country-with-image")
    public ResponseEntity<?> addCountryWithImage(@RequestPart Country country, @RequestPart MultipartFile image) throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();
        String countryJson = objectMapper.writeValueAsString(country);

        System.out.println("[Controller] - Adding country : " + countryJson);

        // Convert image data to Base64 for logging
        String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
        String maskedImageBase64 = imageBase64.replaceAll("(?<=.{10}).*", "...");
        System.out.println("[Controller] - Image Data (Base64): " + maskedImageBase64);
        System.out.println("[Controller] - Image FileName :" + image.getOriginalFilename());
        System.out.println("[Controller] - Image Type :" + image.getContentType());




        try{
            Country c=countryService.addCountryWithImage(country,image);
            return new ResponseEntity<>(c,HttpStatus.CREATED);

        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }



    }







}
