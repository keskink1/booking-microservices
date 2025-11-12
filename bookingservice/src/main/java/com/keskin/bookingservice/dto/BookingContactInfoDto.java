package com.keskin.bookingservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "bookingservice")
public class BookingContactInfoDto {

    private String version;
    private String name;
    private String message;
    private Contact contact;

    @Getter
    @Setter
    public static class Contact {
        private String owner;
        private String email;
    }
}
