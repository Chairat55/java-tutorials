package com.example.springnativequery;

import com.example.springnativequery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringNativeQueryApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringNativeQueryApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			userService.initUser();
		};
	}

}
