package com.example.WireMock;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "REST TEMPLATE", description = "Rest template service", version = "2.0"))
public class WireMockAndSpockTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(WireMockAndSpockTestApplication.class, args);
	}


	}















