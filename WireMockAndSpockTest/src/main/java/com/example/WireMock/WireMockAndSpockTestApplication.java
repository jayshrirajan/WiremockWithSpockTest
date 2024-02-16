package com.example.WireMock;


import com.example.WireMock.controller.LogController;
import com.example.WireMock.model.Item;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "REST TEMPLATE", description = "Rest template service", version = "2.0"))
public class WireMockAndSpockTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(WireMockAndSpockTestApplication.class, args);
		new LogController().index();

	}


	}


















