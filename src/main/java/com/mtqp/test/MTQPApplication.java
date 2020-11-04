package com.mtqp.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MTQPApplication {

	public static void main(String[] args) {

		SpringApplication.run(MTQPApplication.class, args);
	}
}
