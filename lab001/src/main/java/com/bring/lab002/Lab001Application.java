package com.bring.lab002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Lab001Application {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(Lab001Application.class, args);

		User u = context.getBean(User.class);

		u.printUserDetails();


	}

}
