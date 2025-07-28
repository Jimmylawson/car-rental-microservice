package com.jimmyproject.vehileservice.service;


import com.jimmyproject.vehileservice.dtos.VehicleRequestDto;
import com.jimmyproject.vehileservice.dtos.VehicleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleServiceInterface {
    VehicleResponseDto createVehicle(VehicleRequestDto vehicleRequestDto);
    VehicleResponseDto getVehicleById(UUID id);
    VehicleResponseDto updateVehicle(UUID id, VehicleRequestDto vehicleRequestDto);
    Page<VehicleResponseDto> getAllVehicles(Pageable pageable);
    void deleteVehicle(UUID id);

}
