package net.sandeep.grocery.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GroceryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryBackendApplication.class, args);
	}

}
