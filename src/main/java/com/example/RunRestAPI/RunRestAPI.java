package com.example.RunRestAPI;

import com.example.RunRestAPI.run.Location;
import com.example.RunRestAPI.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class RunRestAPI {

	private static final Logger log = LoggerFactory.getLogger(RunRestAPI.class);

	public static void main(String[] args) {
		SpringApplication.run(RunRestAPI.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Run run = new Run(
						1,
						"first run",
						LocalDateTime.now(),
                    	LocalDateTime.now().plusHours(1),
						5,
						Location.INDOOR
					);
			log.info(String.valueOf(run));
			};
		}
	}

