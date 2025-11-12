package com.keskin.bookingservice.dto;

import com.keskin.bookingservice.entity.BookingStatus;

import java.time.LocalDateTime;

public record BookingDto(
        Long id,
        String userName,
        String email,
        LocalDateTime appointmentTime,
        BookingStatus status
) {}
