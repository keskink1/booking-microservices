package com.keskin.notificationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String resourceName,String fieldName, String fieldValue) {
        super(String.format("%s with %s '%s' already exists!", resourceName, fieldName, fieldValue));
    }
}