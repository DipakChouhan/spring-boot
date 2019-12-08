package com.owneroftime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ExcelToDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelToDatabaseApplication.class, args);
	}

}
