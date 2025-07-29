package com.jimmyproject.bookingservice.dtos;


import com.jimmyproject.bookingservice.enums.BookingStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailsDto {
    private UUID bookingId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private double totalPrice;

    // User details from user-service
    private UserResponseDto user;

    // Vehicle details from vehicle-service
    private VehicleResponseDto vehicle;
}