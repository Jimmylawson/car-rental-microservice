package com.jimmyproject.userservice.service;

import com.jimmyproject.userservice.dtos.UserRequestDto;
import com.jimmyproject.userservice.dtos.UserResponseDto;
import com.jimmyproject.userservice.entity.User;
import com.jimmyproject.userservice.mapper.UserMapper;
import com.jimmyproject.userservice.repository.UserRepository;
import com.jimmyproject.userservice.exceptions.DuplicateUserException;
import com.jimmyproject.userservice.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // TODO: Add password encoding when Spring Security is implemented
    // String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
    // userRequestDto.setPassword(encodedPassword);


    public User getUserOrThrowError(UUID userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("id",userId));

    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        return  userMapper.toUserResponseDto(userRepository.save(userMapper.toEntity(userRequestDto)));
    }

    @Override
    public UserResponseDto getUser(UUID userId) {
        return userMapper.toUserResponseDto(getUserOrThrowError(userId));
    }

    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(userMapper::toUserResponseDto);
    }


    @Override
    @Transactional
    public UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto) {
        /// Fetch the user
        var existingUser  = getUserOrThrowError(userId);


        /// Check if email is being changed
        if (!existingUser.getEmail().equals(userRequestDto.getEmail())) {
            /// If email is being changed, check if new email already exists
            userRepository.findByEmail(userRequestDto.getEmail())
                    .ifPresent(user -> {
                        throw new DuplicateUserException("email", userRequestDto.getEmail());
                    });
        }

        existingUser.builder()
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .role(userRequestDto.getRole())
                .enabled(userRequestDto.isEnabled())
                .build();

        /// Update the user
        userRepository.save(existingUser);

        return userMapper.toUserResponseDto(existingUser);

    }

    @Transactional
    @Override
    public UserResponseDto partialUpdateUser(UUID userId, UserRequestDto userRequestDto) {
        var existingUser = getUserOrThrowError(userId);

        if(userRequestDto.getEmail() != null && !existingUser.getEmail().equals(userRequestDto.getEmail())) {
            /// If email is being changed, check if new email already exists
            userRepository.findByEmail(userRequestDto.getEmail())
                    .ifPresent(user -> {
                        throw new DuplicateUserException("email", userRequestDto.getEmail());
                    });
        }

        /// Updated non=null fields from DTO to existing entity
        userMapper.partialUpdateUserFromDto(userRequestDto, existingUser);

        /// Save and return
        var updatedUser = userRepository.save(existingUser);
        return userMapper.toUserResponseDto(updatedUser);
    }
    @Override

    public UserResponseDto findUserByEmail(String email) {
        var user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email",email));
        return  userMapper.toUserResponseDto(user);
    }


    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        var user = getUserOrThrowError(userId);

        userRepository.delete(user);
    }


}
