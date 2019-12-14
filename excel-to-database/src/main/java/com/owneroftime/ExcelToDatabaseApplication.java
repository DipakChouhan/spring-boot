package com.owneroftime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@Configuration
@PropertySources({
    @PropertySource("classpath:messages.properties")
})
public class ExcelToDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelToDatabaseApplication.class, args);
	}
}
