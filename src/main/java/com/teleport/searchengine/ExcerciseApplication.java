package com.teleport.searchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@SpringBootApplication
public class ExcerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcerciseApplication.class, args);
	}

}
