package com.jimmyproject.bookingservice.service;

import com.jimmyproject.bookingservice.dtos.BookingRequestDto;
import com.jimmyproject.bookingservice.dtos.BookingResponseDto;
import com.jimmyproject.bookingservice.entity.Booking;
import com.jimmyproject.bookingservice.exceptions.BookingNotFoundException;
import com.jimmyproject.bookingservice.mapper.BookingMapper;
import com.jimmyproject.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingServiceInterface {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        log.info("Creating new booking for user: {}", bookingRequestDto.getUserId());
        Booking booking = bookingMapper.toEntity(bookingRequestDto);
        Booking savedBooking = bookingRepository.save(booking);
        log.info("Created booking with ID: {}", savedBooking.getId());
        return bookingMapper.toBookingResponseDto(savedBooking);
    }

    @Override
    public BookingResponseDto getBooking(UUID bookingId) {
        log.debug("Fetching booking with ID: {}", bookingId);
        Booking booking = getBookingOrThrowError(bookingId);
        return bookingMapper.toBookingResponseDto(booking);
    }

    @Override
    public Page<BookingResponseDto> getAllBookings(Pageable pageable) {
        log.debug("Fetching all bookings with pagination");
        return bookingRepository.findAll(pageable)
                .map(bookingMapper::toBookingResponseDto);
    }

    @Override
    @Transactional
    public BookingResponseDto updateBooking(UUID bookingId, BookingRequestDto bookingRequestDto) {
        log.info("Updating booking with ID: {}", bookingId);
        Booking existingBooking = getBookingOrThrowError(bookingId);
        
        // Update fields from DTO to existing entity
        bookingMapper.updateBookingFromDto(bookingRequestDto, existingBooking);
        
        // Save the updated booking
        Booking updatedBooking = bookingRepository.save(existingBooking);
        log.info("Updated booking with ID: {}", bookingId);
        
        return bookingMapper.toBookingResponseDto(updatedBooking);
    }

    @Override
    @Transactional
    public void deleteBooking(UUID bookingId) {
        log.info("Deleting booking with ID: {}", bookingId);
        if (!bookingRepository.existsById(bookingId)) {
            throw new BookingNotFoundException(bookingId);
        }
        bookingRepository.deleteById(bookingId);
        log.info("Deleted booking with ID: {}", bookingId);
    }

    @Override
    public Page<BookingResponseDto> getBookingsByUser(UUID userId, Pageable pageable) {
        log.debug("Fetching bookings for user ID: {}", userId);
        Page<Booking> bookings = bookingRepository.findByUserId(userId, pageable);
        
        if (bookings.isEmpty()) {
            log.info("No bookings found for user ID: {}", userId);
        } else {
            log.debug("Found {} bookings for user ID: {}", bookings.getTotalElements(), userId);
        }
        
        return bookings.map(bookingMapper::toBookingResponseDto);
    }

    @Override
    public Page<BookingResponseDto> getBookingsByVehicle(UUID vehicleId, Pageable pageable) {
        log.debug("Fetching bookings for vehicle ID: {}", vehicleId);
        Page<Booking> bookings = bookingRepository.findByVehicleId(vehicleId, pageable);
        
        if (bookings.isEmpty()) {
            log.info("No bookings found for vehicle ID: {}", vehicleId);
        } else {
            log.debug("Found {} bookings for vehicle ID: {}", bookings.getTotalElements(), vehicleId);
        }
        
        return bookings.map(bookingMapper::toBookingResponseDto);
    }

    /*
     * Helper method to find a booking by ID or throw exception if not found
     */
    private Booking getBookingOrThrowError(UUID bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("id", bookingId));
    }

}
