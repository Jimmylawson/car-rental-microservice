package com.jimmyproject.bookingservice.clients;

import com.jimmyproject.bookingservice.dtos.VehicleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("vehicle-service")
public interface VehicleServiceClient {
    @GetMapping("/api/vehicles/{vehicleId}")
    public VehicleResponseDto getVehicleById(@PathVariable UUID vehicleId);

}
