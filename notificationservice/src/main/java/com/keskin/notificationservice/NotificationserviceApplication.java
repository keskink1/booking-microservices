package com.keskin.notificationservice;

import com.keskin.notificationservice.dto.NotificationContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NotificationContactInfoDto.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Notification Microservice REST API Documentation",
				description = "Notification microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Kaan Keskin",
						email = "kaankeskin1@hotmail.com"
				)
		)
)
public class NotificationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
	}

}
