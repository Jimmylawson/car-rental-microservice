package com.jimmyproject.userservice.service;

import com.jimmyproject.userservice.dtos.UserRequestDto;
import com.jimmyproject.userservice.dtos.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface {
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    UserResponseDto getUser(UUID userId);
    Page<UserResponseDto> getAllUsers(Pageable pageable);
    void deleteUser(UUID userId);
    UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto);
    UserResponseDto partialUpdateUser(UUID userId, UserRequestDto userRequestDto);
    UserResponseDto findUserByEmail(String email);



}
