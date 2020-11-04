package com.mtqp.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunner.class);

	public static void main(String[] args) {

		SpringApplication.run(ApplicationRunner.class, args);
	}

	@Override
	public void run(String... args) {

		LOGGER.info("Hello there");
	}
}
