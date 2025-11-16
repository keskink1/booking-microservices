package com.keskin.bookingservice.repository;

import com.keskin.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByEmail(String email);
}