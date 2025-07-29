package com.jimmyproject.bookingservice.dtos;


import com.jimmyproject.bookingservice.enums.Roles;
import lombok.*;

import java.util.UUID;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Roles role;
    private boolean enabled = true;
}
