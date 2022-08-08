package com.qa.filmtvtracker.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(value = "com.qa.filmtvtracker.config")
@EntityScan(value = "com.qa.filmtvtracker.entities")
public class FilmTvTrackerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmTvTrackerProjectApplication.class, args);
		System.out.println("Application started");
	}

}
