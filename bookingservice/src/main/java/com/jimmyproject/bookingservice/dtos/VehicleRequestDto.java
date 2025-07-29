package com.jimmyproject.bookingservice.dtos;

import com.jimmyproject.bookingservice.enums.CarType;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDto {
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
