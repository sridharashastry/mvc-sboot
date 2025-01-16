package com.bring.labs.controller;

import com.bring.labs.model.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {


    private List<Country> countries = new ArrayList<>(List.of(new Country(1, "Portugal", "PT", 100),
            new Country(2, "India", "IN", 200)));




    /*

 Fetch Token from KeyCloak

PS C:\ws> curl -X POST "http://localhost:8080/realms/bringlabs-realm/protocol/openid-connect/token" -H "Content-Type: application/x-www-form-urlencoded" -d "client_id=bringlabs-clientid" -d "client_secret=sLv7dIi62eTZCQPrXeXKwNNVgzi2G6gz" -d "grant_type=client_credentials"
{"access_token":"xxxxxxx","expires_in":300,"refresh_expires_in":0,"token_type":"Bearer","not-before-policy":0,"scope":"email profile"}
PS C:\ws>

Using the Token fetch secured resource

PS C:\ws> curl -X GET http://localhost:8081/countries -H "Authorization: Bearer xxxxx"
[{"id":1,"name":"Portugal","code":"PT","population":100},{"id":2,"name":"India","code":"IN","population":200}]
PS C:\ws>

     */



    @GetMapping("/countries")
    public List<Country> getCountries() {


        System.out.println("\n [BringLabs] Entered (class.method) : " + this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName()+"\n");



        return countries;

    }


}
