package com.jimmyproject.bookingservice.clients;

import com.jimmyproject.bookingservice.dtos.UserResponseDto;
import com.jimmyproject.bookingservice.enums.Roles;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class UserServiceClientFallback implements UserServiceClient {
    
    private static final UserServiceClientFallback INSTANCE = new UserServiceClientFallback();
    
    // Private constructor to prevent instantiation
    private UserServiceClientFallback() {}
    
    // Method to get the singleton instance
    public static UserServiceClientFallback getInstance() {
        return INSTANCE;
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        log.warn("FALLBACK TRIGGERED! Using fallback user for userId: {}", userId);
        log.warn("Error details:", new RuntimeException("Fallback triggered"));
        
        // Create a fallback user
        return UserResponseDto.builder()
                .id(UUID.randomUUID())
                .username("Fallback User")
                .email("fallback@user.com")
                .role(Roles.CUSTOMER)
                .enabled(true)
                .build();
    }
}
