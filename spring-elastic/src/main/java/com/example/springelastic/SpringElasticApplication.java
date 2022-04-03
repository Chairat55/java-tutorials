package com.example.springelastic;

import com.example.springelastic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringElasticApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringElasticApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			userService.initUser();
		};
	}

}
