package com.CSE_D;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomAppApplication.class, args);
		System.out.println("EcomApplication started successfully");
		System.out.println("Connected to Mongodb successfully");
	}

}
