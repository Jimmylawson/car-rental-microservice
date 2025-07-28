package com.jimmyproject.vehicleservice.service;

import com.jimmyproject.vehicleservice.dtos.VehicleRequestDto;
import com.jimmyproject.vehicleservice.dtos.VehicleResponseDto;
import com.jimmyproject.vehicleservice.entity.Vehicle;
import com.jimmyproject.vehicleservice.exceptions.VehicleNotFoundException;
import com.jimmyproject.vehicleservice.mapper.VehicleMapper;
import com.jimmyproject.vehicleservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceImpl implements VehicleServiceInterface {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleResponseDto createVehicle(VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehicleRequestDto);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleResponseDto(savedVehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleResponseDto getVehicleById(UUID id) {
        Vehicle vehicle = getVehicleOrThrowError(id);
        return vehicleMapper.toVehicleResponseDto(vehicle);
    }

    @Override
    public VehicleResponseDto updateVehicle(UUID id, VehicleRequestDto vehicleRequestDto) {
        Vehicle existingVehicle = getVehicleOrThrowError(id);
        vehicleMapper.updateVehicleFromDto(vehicleRequestDto, existingVehicle);
        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toVehicleResponseDto(updatedVehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleResponseDto> getAllVehicles(Pageable pageable) {
        // Ensure page number is not negative
        Pageable validPageable = pageable.isPaged() 
            ? pageable 
            : PageRequest.of(0, 10);
            
        return vehicleRepository.findAll(validPageable)
                .map(vehicleMapper::toVehicleResponseDto);
    }

    @Override
    public void deleteVehicle(UUID id) {
        Vehicle vehicle = getVehicleOrThrowError(id);
        vehicleRepository.delete(vehicle);
    }

    /**
     * Helper method to get a vehicle by ID or throw VehicleNotFoundException
     */
    private Vehicle getVehicleOrThrowError(UUID id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }
}
