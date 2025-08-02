package com.jimmyproject.bookingservice.controller;

import com.jimmyproject.bookingservice.dtos.BookingDetailsDto;
import com.jimmyproject.bookingservice.dtos.BookingRequestDto;
import com.jimmyproject.bookingservice.dtos.BookingResponseDto;
import com.jimmyproject.bookingservice.service.BookingServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

;
import java.util.UUID;

@Tag(name = "Booking Management", description = "APIs for managing bookings")
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingServiceInterface bookingService;

    @Operation(summary = "Create a new booking")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDto createBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.createBooking(bookingRequestDto);
    }

    @Operation(summary = "Get booking by ID")
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> getBooking(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.getBooking(bookingId));
    }

    @Operation(summary = "Get all bookings with pagination")
    @GetMapping
    public ResponseEntity<Page<BookingResponseDto>> getAllBookings(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(bookingService.getAllBookings(pageable));
    }

    @Operation(summary = "Update an existing booking")
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> updateBooking(
            @PathVariable UUID bookingId,
            @Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, bookingRequestDto));
    }

    @Operation(summary = "Delete a booking")
    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable UUID bookingId) {
        bookingService.deleteBooking(bookingId);
    }

    @Operation(summary = "Get bookings by user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<BookingDetailsDto>> getBookingsByUser(
            @PathVariable UUID userId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId, pageable));
    }

    @Operation(summary = "Get bookings by vehicle ID")
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Page<BookingDetailsDto>> getBookingsByVehicle(
            @PathVariable UUID vehicleId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(bookingService.getBookingsByVehicle(vehicleId, pageable));
    }

    @Operation(summary = "Get booking with full details including user and vehicle information")
    @GetMapping("/{bookingId}/details")
    public ResponseEntity<BookingDetailsDto> getBookingWithDetails(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.getBookingWithDetails(bookingId));
    }
}
