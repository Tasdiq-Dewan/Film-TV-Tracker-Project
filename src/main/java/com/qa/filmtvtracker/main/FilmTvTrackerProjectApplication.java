package com.qa.filmtvtracker.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = "com.qa.filmtvtracker.services")
@EntityScan(value = "com.qa.filmtvtracker.entities")
@EnableJpaRepositories("com.qa.filmtvtracker.repo")
public class FilmTvTrackerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmTvTrackerProjectApplication.class, args);
		System.out.println("Application started");
	}

}
