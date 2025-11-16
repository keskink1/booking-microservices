package com.keskin.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record NotificationDto(
        @Schema(description = "Unique ID of the notification", example = "101")
        Long id,

        @Schema(description = "ID of the related booking", example = "123")
        Long bookingId,

        @Schema(description = "Name of the user associated with the notification", example = "John Doe")
        String userName,

        @Schema(description = "Notification message content", example = "Booking created successfully")
        String message,

        @Schema(description = "Status of the notification", example = "PENDING")
        String status,

        @Schema(description = "Timestamp when the notification was created", example = "2025-11-16T14:30:00")
        LocalDateTime createdAt
) {}
