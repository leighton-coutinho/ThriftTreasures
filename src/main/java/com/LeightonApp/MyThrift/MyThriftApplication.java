package com.LeightonApp.MyThrift;

import com.LeightonApp.MyThrift.entity.*;
import com.LeightonApp.MyThrift.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


import java.math.BigDecimal;
import java.util.Date;

@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class
})

@SpringBootApplication
public class MyThriftApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyThriftApplication.class, args);
	}

	// to accept requests from react we need cors configurer

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true)
						.allowedOrigins("http://localhost:3000");
			}
		};
	}



	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(UserService userService,CustomerService customerService, StoreService storeService, CategoryService categoryService, ItemService itemService, SaleService saleService) {
		return runner -> {
			System.out.println("Hello World");

			// Add customer
			Customer customerUser = new Customer("Customer User", "customeruser@example.com", "customeruser", "password1234");
			customerService.save(customerUser);

			// Verify the customer user was added
			System.out.println("Customer User added: " + customerUser);

			// Retrieve the customer user by ID
			Customer retrievedCustomerUser = customerService.findById(customerUser.getUserID()).orElse(null);
			System.out.println("Retrieved Customer User: " + retrievedCustomerUser);

			// Add a random store, FOR ADDING STORE, THE IMAGEPATH SHOULD INCLUDE THE EXTENSION I.E .PNG, .JPG, ETC  
			Store store = new Store("Store Owner", "storeowner@example.com", "storeowner", "password123", "123 Thrift Ave", "storeowner.jpg", "acct_1PWM7wI1dOqpJBRU");
			storeService.save(store);
			System.out.println("Store added: " + store);

			Store retrievedStoreUser = storeService.findById(store.getUserID()).orElse(null);
			System.out.println("Retrieved Customer User: " + retrievedStoreUser);

			Category category = new Category("Accessories");
			categoryService.save(category);
			categoryService.deleteByName("pant");
			System.out.println("Category added: " + category);

			// Add a random item
			Item item = new Item();
			item.setName("Fancy Hat");
			item.setDescription("A fancy hat with feathers.");
			item.setCategory(category);
			item.setPrice(49.99);
			item.setStatus("Available");
			item.setStore(store);
			itemService.save(item);
			System.out.println("Item added: " + item);

			// Add a sale
			//SaleId saleId = new SaleId(retrievedStoreUser, retrievedCustomerUser, item);
			//Sale sale = new Sale(saleId);
			Sale sale = new Sale(retrievedStoreUser, retrievedCustomerUser, item);
			saleService.save(sale);
			System.out.println("Sale added: " + sale);
		};
	}



	/*
	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(UserService userService,
											   StoreService storeService,
											   CustomerService customerService,
											   ItemService itemService,
											   CategoryService categoryService,
											   SaleService saleService) {
		return runner -> {
			System.out.println("Hello World");

			// Add first user for the store
			User storeUser = new User("Store Owner", "storeowner@example.com", "storeowner", "password123");
			userService.save(storeUser);

			// Verify the store user was added
			System.out.println("Store User added: " + storeUser);

			// Retrieve the store user by ID
			User retrievedStoreUser = userService.findById(storeUser.getUserID()).orElse(null);
			System.out.println("Retrieved Store User: " + retrievedStoreUser);

			// Add second user for the customer
			User customerUser = new User("Customer User", "customeruser@example.com", "customeruser", "password1234");
			userService.save(customerUser);

			// Verify the customer user was added
			System.out.println("Customer User added: " + customerUser);

			// Retrieve the customer user by ID
			User retrievedCustomerUser = userService.findById(customerUser.getUserID()).orElse(null);
			System.out.println("Retrieved Customer User: " + retrievedCustomerUser);

			// Add a random store
			Store store = new Store(retrievedStoreUser.getUserID(), "123 Thrift Ave", retrievedStoreUser.getUsername());
			storeService.save(store);
			System.out.println("Store added: " + store);

			// Add a random customer
			Customer customer = new Customer(retrievedCustomerUser);
			customerService.save(customer);
			System.out.println("Customer added: " + customer);

			// Add a random category
			Category category = new Category("Accessories");
			categoryService.save(category);
			System.out.println("Category added: " + category);

			// Add a random item
			Item item = new Item();
			item.setName("Fancy Hat");
			item.setDescription("A fancy hat with feathers.");
			item.setCategory(category);
			item.setPrice(49.99);
			item.setStatus("Available");
			item.setStore(store);
			itemService.save(item);
			System.out.println("Item added: " + item);

			// Add a sale
			SaleId saleId = new SaleId(retrievedStoreUser.getUserID(), retrievedCustomerUser.getUserID(), item.getId());
			Sale sale = new Sale(saleId);
			saleService.save(sale);
			System.out.println("Sale added: " + sale);
		};
	} */
}

