package com.jimmyproject.userservice.dtos;

import com.jimmyproject.userservice.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequestDto {
    @NotBlank
    @Size(min = 3,max = 50)
    private String username;
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotNull
    private Roles role;
    /// the enabled is used to activate or deactivate user account
    private boolean enabled = true; ///Default to true for new users

}
