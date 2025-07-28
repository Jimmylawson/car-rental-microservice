package com.jimmyproject.vehileservice.dtos;

import com.jimmyproject.vehileservice.enums.CarType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleRequestDto {
    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand must be less than 50 characters")
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model must be less than 50 characters")
    private String model;

    @NotBlank(message = "License plate is required")
    @Size(max = 20, message = "License plate must be less than 20 characters")
    private String licensePlate;

    @NotNull(message = "Car type is required")
    private CarType carType;

    @NotNull(message = "Year is required")
    @Min(value = 2010, message = "Year must be 2010 or later")
    @Max(value = 2025, message = "Year cannot be in the future")
    private int year;

    @NotBlank(message = "Color is required")
    @Size(max = 30, message = "Color must be less than 30 characters")
    private String color;

    @NotNull(message = "Seats is required")
    @Min(value = 2, message = "Must have at least 2 seats")
    @Max(value = 20, message = "Cannot have more than 20 seats")
    private int seats;

    @NotNull(message = "Availability is required")
    private boolean available = true;

    @NotBlank(message = "Car duration is required")
    @Size(max = 50, message = "Car duration must be less than 50 characters")
    private String carDuration;

    @NotNull(message = "Rental price is required")
    @Positive(message = "Rental price must be greater than 0")
    private double rentalPrice;
}