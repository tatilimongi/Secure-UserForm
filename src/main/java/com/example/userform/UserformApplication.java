package com.example.userform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.userform")
public class UserformApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserformApplication.class, args);
	}
}
