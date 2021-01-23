package com.test.core.empstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EmpstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpstoreApplication.class, args);
	}

}
