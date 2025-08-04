package com.jimmyproject.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String field, String value) {
        super(String.format("User with %s '%s' already exists", field, value));
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}
