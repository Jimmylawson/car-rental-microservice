package com.jimmyproject.userservice.mapper;

import com.jimmyproject.userservice.dtos.UserRequestDto;
import com.jimmyproject.userservice.dtos.UserResponseDto;
import com.jimmyproject.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);
    User toEntity(UserRequestDto userRequestDto);

    @Mapping(target = "id", ignore = true)  // Prevent ID from being updated
    @Mapping(target = "createdAt", ignore = true)  // Keep original creation timestamp
    @Mapping(target = "password", ignore = true)  // Handle password separately
    void partialUpdateUserFromDto(UserRequestDto dto, @MappingTarget User entity);
}
