package com.example.springgson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class SpringGsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGsonApplication.class, args);

		// 1. Object to JSON
		Gson gson = new Gson(); // Or use new GsonBuilder().create();
		User user = new User();

		String json = gson.toJson(user); // serializes target to Json
		System.out.println(json);

		// 2. JSON to Object
		User user2 = gson.fromJson(json, User.class); // deserializes json into user2
		user2.username = "Username_2";
		user2.firstName = "FirstName_2";
		user2.lastName = "LastName_2";

		String json2 = gson.toJson(user2);
		System.out.println(json2);

		// 3. List<Object> to JSON
		Type listType = new TypeToken<List<User>>() {}.getType();
		List<User> users = new LinkedList<>();
		users.add(new User());
		users.add(new User());

		String json3 = gson.toJson(users, listType);

		// 4. JSON to List<Object>
		List<User> userListFromJson = gson.fromJson(json3, listType);
		System.out.println(userListFromJson);
	}

	private static class User {
		String username = "Username_1";
		String firstName = "FirstName_1";
		String lastName = "LastName_1";
	}

}
