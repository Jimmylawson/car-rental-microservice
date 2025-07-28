package com.jimmyproject.vehicleservice.mapper;

import com.jimmyproject.vehicleservice.dtos.VehicleRequestDto;
import com.jimmyproject.vehicleservice.dtos.VehicleResponseDto;
import com.jimmyproject.vehicleservice.entity.Vehicle;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle toVehicle(VehicleRequestDto vehicleRequestDto);
    
    VehicleResponseDto toVehicleResponseDto(Vehicle vehicle);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVehicleFromDto(VehicleRequestDto dto, @MappingTarget Vehicle entity);
}
