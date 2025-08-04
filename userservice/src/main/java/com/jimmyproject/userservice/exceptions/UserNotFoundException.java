package com.jimmyproject.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String field, Object value) {
        super(String.format("User not found with %s: %s", field, value));
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
