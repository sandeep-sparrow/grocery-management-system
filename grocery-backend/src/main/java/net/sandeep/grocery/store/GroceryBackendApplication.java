package net.sandeep.grocery.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Sandeep R P
 * @version 1.0
 * @license sandeep-sparrow, GITHUB
 * @since 01/01/0001 (MM/DD/YYYY)
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class GroceryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryBackendApplication.class, args);
	}

}
