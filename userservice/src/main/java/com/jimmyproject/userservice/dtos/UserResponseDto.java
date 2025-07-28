package com.jimmyproject.userservice.dtos;


import com.jimmyproject.userservice.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserResponseDto {
    @Schema(description = "User ID", example = "1")
    private UUID id;
    @Schema(description = "User name", example = "John Doe")
    private String username;
    @Schema(description = "User email", example = "KU8oT@example.com")
    private String email;
    @Schema(description = "User role", example = "CUSTOMER")
    private Roles role;
    @Schema(description = "User status", example = "true")
    private boolean enabled;
    @Schema(description = "User creation date", example = "2023-05-01T10:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "User last update date", example = "2023-05-01T10:00:00")
    private LocalDateTime updatedAt;
}
