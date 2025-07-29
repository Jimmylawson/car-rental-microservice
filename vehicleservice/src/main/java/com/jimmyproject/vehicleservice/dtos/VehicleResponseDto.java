package com.jimmyproject.vehicleservice.dtos;

import com.jimmyproject.vehicleservice.enums.CarType;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponseDto {
    private UUID id;
    private String brand;
    private String model;
    private String licensePlate;
    private CarType carType;
    private int manufactureYear;
    private String color;
    private int seats;
    private boolean available;
    private String carDuration;
    private double rentalPrice;
}
