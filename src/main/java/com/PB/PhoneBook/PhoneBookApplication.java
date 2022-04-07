package com.PB.PhoneBook;

import com.PB.PhoneBook.Repository.CustomerRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = CustomerRepo.class)
public class PhoneBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneBookApplication.class, args);
	}

}
