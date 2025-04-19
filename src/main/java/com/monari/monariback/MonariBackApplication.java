package com.monari.monariback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MonariBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonariBackApplication.class, args);
	}

}
