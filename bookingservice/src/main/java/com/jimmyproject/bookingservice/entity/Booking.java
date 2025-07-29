package com.jimmyproject.bookingservice.entity;

import com.jimmyproject.bookingservice.enums.BookingStatus;


import com.jimmyproject.bookingservice.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "bookings")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseEntity {
    private UUID vehicleId;// the vehicle id
    private UUID userId; // the user id
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;


}
