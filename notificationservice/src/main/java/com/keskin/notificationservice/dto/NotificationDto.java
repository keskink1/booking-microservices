package com.keskin.notificationservice.dto;

import com.keskin.notificationservice.entity.NotificationStatus;

import java.time.LocalDateTime;

public record NotificationDto(
        Long id,
        String userName,
        String message,
        NotificationStatus status,
        LocalDateTime createdAt
) {}
