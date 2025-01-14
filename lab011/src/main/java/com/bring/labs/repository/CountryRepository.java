package com.bring.labs.repository;


import com.bring.labs.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT p FROM Country p WHERE " +
            "LOWER(p.countryName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.countryCode) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.population AS string) LIKE CONCAT('%', :keyword, '%')")
    List<Country> searchCountry(String keyword);


}

