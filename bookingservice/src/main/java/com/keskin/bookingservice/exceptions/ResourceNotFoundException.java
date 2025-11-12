package com.keskin.bookingservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s resource not found with the input data %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
