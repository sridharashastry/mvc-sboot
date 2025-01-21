package com.bring.labs.controller;

import com.bring.labs.model.Country;
import com.bring.labs.service.IsolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsolationController {

    private final IsolationService isolationService;

    @Autowired
    public IsolationController(IsolationService isolationService) {
        this.isolationService = isolationService;
    }

    // curl -X GET http://localhost:8080/api/isolation
    @GetMapping("/api/isolation")
    public String testIsolation() {
        System.out.println("Entered the IsolationInvocation");

        new Thread(() -> {
            Country country = new Country();
            country.setCountryName("USA");
            country.setCountryCode("US");
            country.setPopulation("5000000"); // Hardcoded population

            isolationService.updateCountry("1", country);
        }).start();

        try {
            Thread.sleep(2000); // Wait 2 seconds before invoking getCountries
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        isolationService.getCountryById("1");

        return "{ \"data\": \"success\" }";
    }
}
