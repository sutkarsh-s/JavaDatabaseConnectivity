package com.example.springJDBC;

import com.example.springJDBC.model.User;
import com.example.springJDBC.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository repo) {
		return args -> {

			// Insert users
			repo.insertUser(new User("Utkarsh", "utkarsh@gmail.com"));
			repo.insertUser(new User("John", "john@gmail.com"));

			// Fetch all
			repo.getAllUsers().forEach(u ->
					System.out.println(u.getId() + " " + u.getName())
			);

			// Update
			repo.updateUserStatus(1, "INACTIVE");

			// Fetch single
			User user = repo.getUserById(1);
			System.out.println("Updated User: " + user.getStatus());

			// Delete
			repo.deleteUser(2);
		};
	}
}