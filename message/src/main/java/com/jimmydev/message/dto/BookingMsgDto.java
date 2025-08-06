package com.jimmydev.message.dto;

import java.util.UUID;

public record BookingMsgDto (
        String bookingId,
        UUID userId,
        UUID vehicleId,
        String pickupDate,
        String returnDate,
        String bookingStatus,
        String email,
        String phoneNUmber

){

}
