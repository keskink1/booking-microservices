package com.keskin.bookingservice.mapper;

import com.keskin.bookingservice.dto.BookingDto;
import com.keskin.bookingservice.dto.CreateBookingRequestDto;
import com.keskin.bookingservice.dto.UpdateBookingRequestDto;
import com.keskin.bookingservice.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto toDto(Booking booking);

    Booking toEntity(BookingDto bookingDto);

    Booking createRequestToEntity(CreateBookingRequestDto requestDto);

    void updateBookingFromDto(UpdateBookingRequestDto requestDto, @MappingTarget Booking booking);
}
