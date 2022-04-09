package com.example.springgson;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGsonApplication.class, args);

		Gson gson = new Gson(); // Or use new GsonBuilder().create();
		User user = new User();

		String json = gson.toJson(user); // serializes target to Json
		System.out.println(json);

		User user2 = gson.fromJson(json, User.class); // deserializes json into user2
		user2.username = "Username_2";
		user2.firstName = "FirstName_2";
		user2.lastName = "LastName_2";

		String json2 = gson.toJson(user2);
		System.out.println(json2);
	}

	private static class User {
		String username = "Username_1";
		String firstName = "FirstName_1";
		String lastName = "LastName_1";
	}

}
