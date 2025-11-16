package com.keskin.bookingservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.keskin.bookingservice.entity.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record BookingDto(
        @Schema(description = "Unique ID of the booking", example = "123")
        Long id,

        @Schema(description = "Name of the user who made the booking", example = "John Doe")
        String userName,

        @Schema(description = "Email of the user", example = "john.doe@example.com")
        String email,

        @Schema(description = "Scheduled appointment time", example = "2025-12-01T14:30:00")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime appointmentTime,

        @Schema(description = "Status of the booking", example = "CONFIRMED")
        BookingStatus status
) {}
