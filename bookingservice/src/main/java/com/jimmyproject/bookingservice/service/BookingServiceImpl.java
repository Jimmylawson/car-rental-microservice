package com.jimmyproject.bookingservice.service;

import com.jimmyproject.bookingservice.clients.UserServiceClient;
import com.jimmyproject.bookingservice.clients.VehicleServiceClient;
import com.jimmyproject.bookingservice.dtos.*;
import com.jimmyproject.bookingservice.entity.Booking;
import com.jimmyproject.bookingservice.exception.ResourceNotFoundException;
import com.jimmyproject.bookingservice.exceptions.BookingNotFoundException;
import com.jimmyproject.bookingservice.mapper.BookingMapper;
import com.jimmyproject.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingServiceInterface {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserServiceClient userServiceClient;
    private final VehicleServiceClient vehicleServiceClient;
    private final StreamBridge streamBridge;

    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        log.info("Creating new booking for user: {}", bookingRequestDto.getUserId());
        Booking booking = bookingMapper.toEntity(bookingRequestDto);
        Booking savedBooking = bookingRepository.save(booking);
        sendCommunication(savedBooking);
        log.info("Created booking with ID: {}", savedBooking.getId());
        return bookingMapper.toBookingResponseDto(savedBooking);
    }



    private void sendCommunication(Booking savedBooking) {
        // Send communication using StreamBridge
        BookingMsgDto bookingMsgDto = new BookingMsgDto(
                savedBooking.getId(),
                savedBooking.getUserId(),
                savedBooking.getVehicleId(),
                savedBooking.getStartDate(),
                savedBooking.getEndDate(),
                savedBooking.getStatus().name(),
                userServiceClient.getUserById(savedBooking.getUserId()).getEmail()
        );
       var result =  streamBridge.send("communication-out-0", bookingMsgDto);
        log.info("Is the Communication request successfully processed? {}", result);
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
    public Page<BookingDetailsDto> getBookingsByUser(UUID userId, Pageable pageable) {
        log.debug("Fetching bookings for user ID: {}", userId);
        return bookingRepository.findByUserId(userId, pageable)
                .map(this::enrichBookingWithDetails);

    }

    @Override
    public Page<BookingDetailsDto> getBookingsByVehicle(UUID vehicleId, Pageable pageable) {
        return bookingRepository.findByVehicleId(vehicleId, pageable)
                .map(this::enrichBookingWithDetails);
    }
    @Override
    public BookingDetailsDto getBookingWithDetails(UUID bookingId) {
        var booking = getBookingOrThrowError(bookingId);
        var vehicle = vehicleServiceClient.getVehicleById(booking.getVehicleId());
        var user = userServiceClient.getUserById(booking.getUserId());

        //Build and return the response
        return BookingDetailsDto.builder()
                .bookingId(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .status(booking.getStatus())
                .vehicle(vehicle)
                .user(user)
                .build();
    }

    /*
     * Helper method to find a booking by ID or throw exception if not found
     */
    private Booking getBookingOrThrowError(UUID bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("id", bookingId));
    }

    // Helper method to enrich a booking with user and vehicle details
    private BookingDetailsDto enrichBookingWithDetails(Booking booking) {
        // Use CompletableFuture to asynchronously fetch user and vehicle details
        CompletableFuture<VehicleResponseDto> vehicleFuture = CompletableFuture.supplyAsync(
                () -> vehicleServiceClient.getVehicleById(booking.getVehicleId()));
        CompletableFuture<UserResponseDto> userFuture = CompletableFuture.supplyAsync(
                () -> userServiceClient.getUserById(booking.getUserId()));

        return CompletableFuture.allOf(vehicleFuture, userFuture)
                .thenApply(v -> BookingDetailsDto.builder()
                        .bookingId(booking.getId())
                        .startDate(booking.getStartDate())
                        .endDate(booking.getEndDate())
                        .status(booking.getStatus())
                        .vehicle(vehicleFuture.join())
                        .user(userFuture.join())
                        .build())
                .join();
    }

    @Override
    public boolean updateCommunicationStatus(UUID bookingId) {
        boolean isUpdated = false;
        if(bookingId != null){
            var booking = bookingRepository.findById(bookingId).orElseThrow(
                    ()-> new ResourceNotFoundException("Booking", "id", bookingId));
        booking.setCommunicationSent(true);
        bookingRepository.save(booking);
        isUpdated = true;
        }

        return isUpdated;

    }

}
