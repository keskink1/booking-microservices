package com.keskin.bookingservice.service.impl;

import com.keskin.bookingservice.dto.BookingDto;
import com.keskin.bookingservice.dto.CreateBookingRequestDto;
import com.keskin.bookingservice.dto.UpdateBookingRequestDto;
import com.keskin.bookingservice.entity.Booking;
import com.keskin.bookingservice.exceptions.ResourceNotFoundException;
import com.keskin.bookingservice.mapper.BookingMapper;
import com.keskin.bookingservice.repository.BookingRepository;
import com.keskin.bookingservice.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto createBooking(CreateBookingRequestDto requestDto) {
        Booking booking = bookingMapper.createRequestToEntity(requestDto);
        Booking saved = bookingRepository.save(booking);

        return bookingMapper.toDto(saved);
    }

    @Override
    public BookingDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID", "Booking", id.toString()));
        return bookingMapper.toDto(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateBooking(Long id, UpdateBookingRequestDto requestDto) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);

        if (existingBooking.isEmpty()) {
            return false;
        }

        Booking bookingToUpdate = existingBooking.get();

        bookingMapper.updateBookingFromDto(requestDto, bookingToUpdate);

        bookingRepository.save(bookingToUpdate);
        return true;
    }

    @Override
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
