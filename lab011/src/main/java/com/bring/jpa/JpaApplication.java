package com.bring.jpa;

import com.bring.jpa.model.Country;
import com.bring.jpa.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(CountryRepository countryRepository) {
		return args -> {
			countryRepository.save(loadObject("IN","India",1000));
			countryRepository.save(loadObject("PT","Portugal",400));

			//keep adding as many countries we want.
			//only thing is ensure country image is loaded in resources with naming convention
		};
	}

	public Country loadObject(String countryCode, String countryName, int population) throws IOException {

		System.out.println("Loading data into database. Details :"+countryCode+" and "+countryName);

		ClassPathResource imageResource = new ClassPathResource(countryCode+"-country-image.png");
		byte[] imageBytes = Files.readAllBytes(Path.of(imageResource.getURI()));

		// Create and save the Country entity
		Country country = new Country();
		country.setCountryCode(countryCode);
		country.setCountryName(countryName);
		country.setImageName(countryCode+"-country-image.png");
		country.setImageType("image/png");
		country.setCountryImage(imageBytes);
		country.setPopulation(population);

		return country;
	}


}
