package com.example.vrv_assignment.vrv_assignment;

import com.example.vrv_assignment.vrv_assignment.auth.AuthenticationService;
import com.example.vrv_assignment.vrv_assignment.auth.RegisterRequest;
import com.example.vrv_assignment.vrv_assignment.item.Item;
import com.example.vrv_assignment.vrv_assignment.item.ItemRepository;
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
	public CommandLineRunner commandLineRunner(AuthenticationService service, ItemRepository itemRepository) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Adminuuuu")
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
			itemRepository.save(new Item(1L, "Item 1", "Description for Item 1", "Category A"));
			itemRepository.save(new Item(2L, "Item 2", "Description for Item 2", "Category B"));
			itemRepository.save(new Item(3L, "Item 3", "Description for Item 3", "Category C"));
			itemRepository.save(new Item(4L, "Item 4", "Description for Item 4", "Category A"));
			itemRepository.save(new Item(5L, "Item 5", "Description for Item 5", "Category B"));
			itemRepository.save(new Item(6L, "Item 6", "Description for Item 6", "Category C"));

			System.out.println("Sample data initialized.");
		};
	}
}
