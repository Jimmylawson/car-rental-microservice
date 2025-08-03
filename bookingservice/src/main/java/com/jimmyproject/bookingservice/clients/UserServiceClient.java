package com.jimmyproject.bookingservice.clients;

import com.jimmyproject.bookingservice.dtos.UserResponseDto;
import com.jimmyproject.bookingservice.enums.Roles;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    
    @GetMapping("/api/users/{userId}")
    @CircuitBreaker(name = "userService", fallbackMethod = "getUserByIdFallback")
    @Retryable(
        value = { Exception.class },
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2.0)
    )
    UserResponseDto getUserById(@PathVariable UUID userId);
    
    // Fallback method must have the same signature plus a Throwable parameter
    default UserResponseDto getUserByIdFallback(UUID userId, Throwable t) {
        System.out.println("Fallback triggered for user: " + userId + ", error: " + t.getMessage());
        return UserResponseDto.builder()
                .id(UUID.randomUUID())
                .username("Fallback User")
                .email("fallback@user.com")
                .role(Roles.CUSTOMER)
                .enabled(true)
                .build();
    }
}
