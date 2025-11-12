package com.keskin.bookingservice.constants;

public class BookingConstants {

    private BookingConstants() {
        // Prevent instantiation
    }

    public static final String BOOKING_CREATED = "Booking created successfully";
    public static final String BOOKING_FETCHED = "Booking fetched successfully";
    public static final String BOOKING_DELETED = "Booking deleted successfully";
    public static final String BOOKING_UPDATED = "Booking updated successfully";
    public static final String BOOKING_DELETE_FAILED = "Booking delete failed";
    public static final String BOOKING_UPDATE_FAILED = "Booking update failed";
    public static final String BOOKING_NOT_FOUND = "Booking not found";

    public static final String HTTP_STATUS_200 = "OK";
    public static final String HTTP_STATUS_201 = "Created";
    public static final String HTTP_STATUS_204 = "No Content";
    public static final String HTTP_STATUS_404 = "Not Found";
    public static final String HTTP_STATUS_417 = "Operation Failed";
    public static final String HTTP_STATUS_500 = "Internal Server Error";
}
