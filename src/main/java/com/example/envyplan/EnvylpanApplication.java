package com.example.envyplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.envyplan.repository")
@ComponentScan(basePackages = "com.example.envyplan")
public class EnvylpanApplication {

	public static void main(String[] args) {

		SpringApplication.run(EnvylpanApplication.class, args);
	}

}
