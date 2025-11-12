package com.keskin.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "notificationservice")
public class NotificationContactInfoDto {

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
