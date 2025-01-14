package com.bring.labs.controller;


import com.bring.labs.model.Country;
import com.bring.labs.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class GetCountryController {

    @Autowired
    CountryService countryService;



    ////////////////////READ-ALL///////////////////////////////////


    //curl -i -X GET http://localhost:8080/countries   (use this to get http response headers. By default Spring does not send any http headers)

    //Important: if we want http headers to be explicitly controlled and sent back to caller, we need to use ResponseEntity
    @RequestMapping("/countries")
    public ResponseEntity<List<Country>> getCountries() {
        System.out.println("[controllers] - Fetching list of countries");
        List<Country> countries = countryService.getCountries();
        return ResponseEntity.ok()
                .header("conversationid", "1111")
                .body(countries);
    }





    ////////////////////READ-SINGLE///////////////////////////////////


    //curl -i -X GET http://localhost:8080/countries/1
    //We can use annotation @GetMapping if we want explicitly define a get.
    @GetMapping("/countries/{countryId}")
    public ResponseEntity<Object> getCountryById(@PathVariable String countryId) {

        System.out.println("[controllers] - Fetching country by countryId: " + countryId);

        Country country = countryService.getCountryById(countryId);

        // Check if the retrieved country object itself is null
        // or if any of its essential fields (e.g., countryCode, countryName) are null
        if (country == null ||
                (country.getCountryCode() == null && country.getCountryName() == null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country with ID " + countryId + " not found.");
        } else {

            Country countryForLogging = new Country(country.getCountryId(), country.getCountryCode(), country.getCountryName(), country.getImageName(), country.getImageType(), "[MASKED]".getBytes());

            System.out.println("[controllers] - Returning country: " + countryForLogging);

            return ResponseEntity.ok(country);
        }
    }




    ////////////////////READ-SINGLE-BUT-ONLY-IMAGE-IN-BASE64///////////////////////////////////



    //curl -i -X GET http://localhost:8080/countries/1/image-base64

    //on browser : http://localhost:8080/countries/1/image-base64






    @GetMapping("/countries/{countryId}/image-base64")
    public ResponseEntity<?> getImageInBase64(@PathVariable String countryId) throws JsonProcessingException {
        Country country = countryService.getCountryById(countryId);
        if (country == null || country.getCountryImage() == null) {
            return ResponseEntity.notFound().build();
        }

        // Extract image type from imageName (assuming imageName is like "image.png")
        String imageType = extractImageTypeFromFileName(country.getImageName());

        // Encode image data to Base64 string
        String base64Image = Base64.getEncoder().encodeToString(country.getCountryImage());

        // Create JSON response with image data
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(
                Map.of("data", "data:image/" + imageType + ";base64," + base64Image)
        );

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(jsonResponse);
    }













    ////////////////////READ-SINGLE-BUT-ONLY-IMAGE-WITHOUT-JSON-OBJECT///////////////////////////////////



    //curl -i -X GET http://localhost:8080/countries/1/image

    //on browser : http://localhost:8080/countries/1/image
    //USE THIS ON BROWSER TO GET THE IMAGE DIRECTLY BEING DISPLAYED



    @GetMapping("/countries/{countryId}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable String countryId) {
        Country country = countryService.getCountryById(countryId);
        if (country == null || country.getCountryImage() == null) {
            return ResponseEntity.notFound().build();
        }

        // Extract image type from imageName (assuming imageName is like "image.png")
        String imageType = extractImageTypeFromFileName(country.getImageName());

        return ResponseEntity.ok()
                .header("Content-Type", imageType)
                .body(country.getCountryImage());
    }



    ////////BELOW IS HELPER METHOD //////////////////////

    // Helper method to extract image type from filename
    private String extractImageTypeFromFileName(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return "image/" + fileName.substring(lastDotIndex + 1);
        } else {
            return "image/unknown";
        }
    }

}
