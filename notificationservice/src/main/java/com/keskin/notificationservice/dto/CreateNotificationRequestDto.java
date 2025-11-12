package com.keskin.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationRequestDto {
    @NotBlank(message = "User name cannot be empty")
    String userName;
    @NotBlank(message = "Message cannot be empty")
    String message;
}
