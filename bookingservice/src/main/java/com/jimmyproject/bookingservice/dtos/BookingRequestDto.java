package com.jimmyproject.bookingservice.dtos;

import com.jimmyproject.bookingservice.entity.Booking;
import com.jimmyproject.bookingservice.enums.BookingStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDto {
    @NotNull(message = "Vehicle ID is required")
    private UUID vehicleId;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;

    @Positive(message = "Total price must be positive")
    private double totalPrice;

    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;

    // Custom validation method to ensure end date is after start date
    public boolean isEndDateAfterStartDate() {
        return endDate != null && endDate.isAfter(startDate);
    }

}
