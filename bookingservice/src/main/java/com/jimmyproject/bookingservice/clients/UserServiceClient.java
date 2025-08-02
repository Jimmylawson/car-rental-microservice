package com.jimmyproject.bookingservice.clients;

import com.jimmyproject.bookingservice.dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
    name = "user-service",
    fallbackFactory = UserServiceClientFallbackFactory.class
)
public interface UserServiceClient {
    @GetMapping("/api/users/{userId}")
    UserResponseDto getUserById(@PathVariable UUID userId);
}
