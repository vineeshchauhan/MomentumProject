package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MomentumProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentumProjectApplication.class, args);
	}

}
