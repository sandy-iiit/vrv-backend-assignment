package com.example.vrv_assignment.vrv_assignment;

import com.example.vrv_assignment.vrv_assignment.auth.AuthenticationService;
import com.example.vrv_assignment.vrv_assignment.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.vrv_assignment.vrv_assignment.user.Role.*;

@SpringBootApplication
public class VrvAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(VrvAssignmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Ad")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("Mn")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

			var usr = RegisterRequest.builder()
					.firstname("User")
					.lastname("U")
					.email("user@mail.com")
					.password("password")
					.role(USER)
					.build();
			System.out.println("User token: " + service.register(usr).getAccessToken());

		};
	}
}
