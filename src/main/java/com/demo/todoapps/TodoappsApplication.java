package com.demo.todoapps;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class TodoappsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappsApplication.class, args);
	}

}
