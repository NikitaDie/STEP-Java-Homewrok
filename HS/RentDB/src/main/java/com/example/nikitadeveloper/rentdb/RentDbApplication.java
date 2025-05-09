package com.example.nikitadeveloper.rentdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
	org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
	org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration.class
})
public class RentDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentDbApplication.class, args);
	}

}
