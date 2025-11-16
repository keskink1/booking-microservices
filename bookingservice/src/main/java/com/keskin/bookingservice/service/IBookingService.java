package com.keskin.bookingservice.service;

import com.keskin.bookingservice.dto.BookingDto;
import com.keskin.bookingservice.dto.CreateBookingRequestDto;
import com.keskin.bookingservice.dto.UpdateBookingRequestDto;

import java.util.List;

public interface IBookingService {

    BookingDto createBooking(CreateBookingRequestDto requestDto);

    BookingDto getBookingById(Long id);

    List<BookingDto> getAllBookings();

     boolean updateBooking(Long id, UpdateBookingRequestDto requestDto);

    boolean deleteBooking(Long id);

}