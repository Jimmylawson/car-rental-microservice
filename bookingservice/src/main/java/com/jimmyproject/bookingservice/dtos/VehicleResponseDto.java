package com.jimmyproject.bookingservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmyproject.bookingservice.enums.CarType;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleResponseDto {
    private UUID id;
    private String brand;
    private String model;
    private String licensePlate;
    private CarType carType;
    private int manufactureYear;
    private String color;
    private int seats;
    private boolean available = true;
    private String carDuration;
    private double rentalPrice;
}
