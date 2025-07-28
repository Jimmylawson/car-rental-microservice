package com.jimmyproject.vehileservice.dtos;

import com.jimmyproject.vehileservice.enums.CarType;
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
    private int year;
    private String color;
    private int seats;
    private boolean available;
    private String carDuration;
    private double rentalPrice;
}
