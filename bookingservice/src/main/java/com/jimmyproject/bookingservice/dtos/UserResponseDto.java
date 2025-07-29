package com.jimmyproject.bookingservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmyproject.bookingservice.enums.Roles;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private UUID id;
    private String username;
    private String email;
    private Roles role;
    private boolean enabled;

}
