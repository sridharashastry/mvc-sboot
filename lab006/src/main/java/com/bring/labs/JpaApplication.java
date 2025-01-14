package com.bring.labs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	@Bean
	public CommandLineRunner run() {
		return args -> {
			executeCurl("curl -X POST http://localhost:8080/countries -H \"Content-Type: application/json\" -d \"{\\\"countryCode\\\": \\\"US\\\", \\\"countryName\\\": \\\"United States\\\"}\"");
			executeCurl("curl -X POST http://localhost:8080/countries -H \"Content-Type: application/json\" -d \"{\\\"countryCode\\\": \\\"IN\\\", \\\"countryName\\\": \\\"India\\\"}\"");
			executeCurl("curl -X POST http://localhost:8080/countries -H \"Content-Type: application/json\" -d \"{\\\"countryCode\\\": \\\"PT\\\", \\\"countryName\\\": \\\"PORTUGAL\\\"}\"");
		};
	}




	private void executeCurl(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
