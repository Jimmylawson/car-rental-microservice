package com.jimmyproject.bookingservice.exceptions;


import com.jimmyproject.bookingservice.exception.ResourceNotFoundException;

public class BookingNotFoundException extends ResourceNotFoundException {
    public BookingNotFoundException(String fieldName, Object bookingNotFound) {
        super("Booking",fieldName, bookingNotFound);
    }
    public BookingNotFoundException(Object bookingId) {
        this("id", bookingId);
    }
}
