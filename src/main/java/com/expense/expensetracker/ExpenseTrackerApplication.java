package com.expense.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication .class, args);
	}

	/*
	 * @Bean public OpenAPI baseOpenAPI() {
	 * 
	 * return new OpenAPI().info(new
	 * Info().title("Spring Doc").version("1.0.0").description("Spring doc")); }
	 */
}
