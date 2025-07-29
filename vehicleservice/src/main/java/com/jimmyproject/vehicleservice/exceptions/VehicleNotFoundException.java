package com.jimmyproject.vehicleservice.exceptions;

public class VehicleNotFoundException extends ResourceNotFoundException {
    public VehicleNotFoundException(Object fieldValue) {
        super("Vehicle", "id", fieldValue);
    }
//    public VehicleNotFoundException(UUID vehicleId) {
//        this("id",vehicleId);
//    }
}
