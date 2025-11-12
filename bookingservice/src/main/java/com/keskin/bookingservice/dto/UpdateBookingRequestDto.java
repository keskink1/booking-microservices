package com.keskin.bookingservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UpdateBookingRequestDto(
        @NotNull(message = "Appointment time cannot be null")
        @Future(message = "Appointment time must be in the future")
        LocalDateTime appointmentTime
) {}
