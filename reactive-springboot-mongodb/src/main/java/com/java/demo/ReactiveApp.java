package com.java.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ReactiveApp {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApp.class, args);
	}

}
