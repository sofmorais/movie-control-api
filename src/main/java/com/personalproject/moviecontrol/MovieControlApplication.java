package com.personalproject.moviecontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

@SpringBootApplication
public class MovieControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieControlApplication.class, args);
	}

}
