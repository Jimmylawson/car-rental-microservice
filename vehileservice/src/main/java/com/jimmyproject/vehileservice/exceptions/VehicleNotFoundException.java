package com.jimmyproject.vehileservice.exceptions;

import com.jimmyproject.utils.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class VehicleNotFoundException extends ResourceNotFoundException {
    public VehicleNotFoundException(Object fieldValue) {
        super("Vehicle", "id", fieldValue);
    }
//    public VehicleNotFoundException(UUID vehicleId) {
//        this("id",vehicleId);
//    }
}
