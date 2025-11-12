package com.keskin.bookingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequestDto {
    @NotBlank(message = "User name cannot be empty")
    String userName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email address")
    String email;

    @NotNull(message = "Appointment time cannot be null")
    @Future(message = "Appointment time must be in the future")
    LocalDateTime appointmentTime;
}
