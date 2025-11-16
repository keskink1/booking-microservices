package com.keskin.message.dto;



import java.time.LocalDateTime;

public record BookingMsgDto(
        Long id,
        String email,
        String userName,
        String status,
        LocalDateTime appointmentTime,
        LocalDateTime createdAt
) {}