package com.jimmyproject.bookingservice.enums;

public enum BookingStatus {
    PENDING,        // Booking created but not yet confirmed
    CONFIRMED,      // Booking confirmed and vehicle reserved
    CANCELLED,      // Booking cancelled by user or system
    COMPLETED,      // Booking completed successfully
    REJECTED,       // Booking rejected (e.g. no availability or payment failed)
    IN_PROGRESS     // Vehicle has been picked up, booking is active
}