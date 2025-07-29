package com.jimmyproject.vehicleservice.controller;

import com.jimmyproject.vehicleservice.dtos.VehicleRequestDto;
import com.jimmyproject.vehicleservice.dtos.VehicleResponseDto;
import com.jimmyproject.vehicleservice.service.VehicleServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Vehicle Management", description = "APIs for managing vehicles")
@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    
    private final VehicleServiceInterface vehicleService;

    @Operation(summary = "Create a new vehicle")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponseDto createVehicle(@Valid @RequestBody VehicleRequestDto vehicleRequestDto) {
        return vehicleService.createVehicle(vehicleRequestDto);
    }

    @Operation(summary = "Get vehicle by ID")
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> getVehicle(@PathVariable UUID id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @Operation(summary = "Update a vehicle (full update)")
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> updateVehicle(
            @PathVariable UUID id,
            @Valid @RequestBody VehicleRequestDto vehicleRequestDto) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleRequestDto));
    }

    @Operation(summary = "Get all vehicles with pagination")
    @GetMapping
    public ResponseEntity<Page<VehicleResponseDto>> getAllVehicles(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(vehicleService.getAllVehicles(pageable));
    }

    @Operation(summary = "Delete a vehicle")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable UUID id) {
        vehicleService.deleteVehicle(id);
    }
}
