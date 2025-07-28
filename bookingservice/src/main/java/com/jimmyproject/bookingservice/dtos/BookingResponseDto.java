package com.jimmyproject.bookingservice.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmyproject.bookingservice.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponseDto {
    @Schema(description = "Unique identifier of the booking", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "ID of the vehicle being booked", example = "123e4567-e89b-12d3-a456-426614174001")
    private UUID vehicleId;

    @Schema(description = "ID of the user making the booking", example = "123e4567-e89b-12d3-a456-426614174002")
    private UUID userId;

    @Schema(description = "Start date and time of the booking", example = "2025-08-01T10:00:00")
    private LocalDateTime startDate;

    @Schema(description = "End date and time of the booking", example = "2025-08-05T10:00:00")
    private LocalDateTime endDate;

    @Schema(description = "Total price for the booking", example = "299.99")
    private double totalPrice;

    @Schema(description = "Current status of the booking")
    private BookingStatus status;
}