package com.jimmyproject.bookingservice.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookingMsgDto(UUID bookingId,
                            UUID userId,
                            UUID vehicleId,
                            LocalDateTime pickupDate,
                            LocalDateTime returnDate,
                            String bookingStatus,
                            String email){
}
