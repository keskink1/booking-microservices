package com.keskin.bookingservice.controller;

import com.keskin.bookingservice.constants.BookingConstants;
import com.keskin.bookingservice.dto.*;
import com.keskin.bookingservice.service.IBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Controller", description = "Endpoints for managing bookings")
public class BookingController {

    private final IBookingService bookingService;
    private final BookingContactInfoDto bookingContactInfoDto;

    @GetMapping("/test")
    @Operation(summary = "Test Booking Service", description = "Returns a test message after 5 seconds delay")
    public Mono<String> testBooking() {
        return Mono.delay(Duration.ofSeconds(5))
                .map(t -> "Booking service is working");
    }

    @PostMapping("/create")
    @Operation(summary = "Create Booking", description = "Create a new booking")
    public ResponseEntity<ApiResponse<BookingDto>> createBooking(
            @Valid @RequestBody CreateBookingRequestDto requestDto,
            UriComponentsBuilder uriBuilder
    ) {
        BookingDto created = bookingService.createBooking(requestDto);

        URI location = uriBuilder
                .path("/api/bookings/{id}")
                .buildAndExpand(created.id())
                .toUri();

        ApiResponse<BookingDto> response = new ApiResponse<>(
                BookingConstants.BOOKING_CREATED,
                BookingConstants.HTTP_STATUS_201,
                created
        );

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Booking by ID", description = "Fetch a single booking by its ID")
    public ResponseEntity<ApiResponse<BookingDto>> getBooking(@PathVariable Long id) {
        BookingDto booking = bookingService.getBookingById(id);

        ApiResponse<BookingDto> response = new ApiResponse<>(
                BookingConstants.BOOKING_FETCHED,
                BookingConstants.HTTP_STATUS_200,
                booking
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All Bookings", description = "Fetch all bookings")
    public ResponseEntity<ApiResponse<List<BookingDto>>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings();

        ApiResponse<List<BookingDto>> response = new ApiResponse<>(
                BookingConstants.BOOKING_FETCHED,
                BookingConstants.HTTP_STATUS_200,
                bookings
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update Booking", description = "Update a booking by its ID")
    public ResponseEntity<ApiResponse<BookingDto>> updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookingRequestDto requestDto
    ) {
        boolean updated = bookingService.updateBooking(id, requestDto);

        ApiResponse<BookingDto> response;
        if (updated) {
            BookingDto updatedDto = bookingService.getBookingById(id);
            response = new ApiResponse<>(
                    BookingConstants.BOOKING_UPDATED,
                    BookingConstants.HTTP_STATUS_200,
                    updatedDto
            );
        } else {
            response = new ApiResponse<>(
                    BookingConstants.BOOKING_UPDATE_FAILED,
                    BookingConstants.HTTP_STATUS_417,
                    null
            );
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Booking", description = "Delete a booking by its ID")
    public ResponseEntity<ApiResponse<Boolean>> deleteBooking(@PathVariable Long id) {
        boolean deleted = bookingService.deleteBooking(id);

        ApiResponse<Boolean> response;
        if (deleted) {
            response = new ApiResponse<>(
                    BookingConstants.BOOKING_DELETED,
                    BookingConstants.HTTP_STATUS_204,
                    null
            );
        } else {
            response = new ApiResponse<>(
                    BookingConstants.BOOKING_DELETE_FAILED,
                    BookingConstants.HTTP_STATUS_417,
                    null
            );
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/contact-info")
    @Operation(summary = "Get Contact Info", description = "Fetch contact information for the booking service")
    public ResponseEntity<BookingContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookingContactInfoDto);
    }
}
