package com.jimmyproject.vehicleservice.entity;


import com.jimmyproject.utils.entity.BaseEntity;
import com.jimmyproject.vehicleservice.enums.CarType;
import jakarta.persistence.*;
import lombok.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {
    private String brand; // e.g Toyota,Mercedes
    private String model; // e.g Corolla,
    @Column(unique = true,nullable = false)
    private String licensePlate; // Unique license plate
    @Enumerated(EnumType.STRING)
    @Column(name="car_type")
    private CarType carType; // e.g SUV,SEDAN,TRUCK
    @Column(name = "manufacture_year")
    private int manufactureYear;
    private String color; // e.g Red,Blue
    private int seats; // Number of seats
    private boolean available; // Is it currently rentable
    private String carDuration; // Duration of the car meant to be daily, weekly
    private double rentalPrice; //Price per day


}
