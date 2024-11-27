package com.example.RunRestAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunRestAPI {

	private static final Logger log = LoggerFactory.getLogger(RunRestAPI.class);

	public static void main(String[] args) {
		SpringApplication.run(RunRestAPI.class, args);
	}
}
