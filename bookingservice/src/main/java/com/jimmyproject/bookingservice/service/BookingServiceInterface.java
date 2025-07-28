package com.jimmyproject.bookingservice.service;


import com.jimmyproject.bookingservice.dtos.BookingRequestDto;
import com.jimmyproject.bookingservice.dtos.BookingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookingServiceInterface {
    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);
    BookingResponseDto getBooking(UUID bookingId);
    Page<BookingResponseDto> getBookingsByUser(UUID userId, Pageable pageable);
    Page<BookingResponseDto> getBookingsByVehicle(UUID vehicleId, Pageable pageable);
    Page<BookingResponseDto> getAllBookings(Pageable pageable);
    BookingResponseDto updateBooking(UUID bookingId, BookingRequestDto bookingRequestDto);
    void deleteBooking(UUID bookingId);
}
