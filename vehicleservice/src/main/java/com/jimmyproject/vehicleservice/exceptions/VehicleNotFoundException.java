package com.jimmyproject.vehicleservice.exceptions;

import com.jimmyproject.utils.exceptions.ResourceNotFoundException;

public class VehicleNotFoundException extends ResourceNotFoundException {
    public VehicleNotFoundException(Object fieldValue) {
        super("Vehicle", "id", fieldValue);
    }
//    public VehicleNotFoundException(UUID vehicleId) {
//        this("id",vehicleId);
//    }
}
