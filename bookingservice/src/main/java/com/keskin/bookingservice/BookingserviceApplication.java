package com.keskin.bookingservice;

import com.keskin.bookingservice.dto.BookingContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BookingContactInfoDto.class)
@OpenAPIDefinition(
        info = @Info(
                title = "Booking Microservice REST API Documentation",
                description = "Booking microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Kaan Keskin",
                        email = "kaankeskin1@hotmail.com"
                )
        )
)

public class BookingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingserviceApplication.class, args);
    }

}
