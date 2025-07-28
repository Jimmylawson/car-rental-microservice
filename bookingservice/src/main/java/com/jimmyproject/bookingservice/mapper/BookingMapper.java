package com.jimmyproject.bookingservice.mapper;

import com.jimmyproject.bookingservice.dtos.BookingRequestDto;
import com.jimmyproject.bookingservice.dtos.BookingResponseDto;
import com.jimmyproject.bookingservice.entity.Booking;
import org.mapstruct.*;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {
    BookingResponseDto toBookingResponseDto(Booking booking);
    Booking toEntity(BookingRequestDto bookingRequestDto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateBookingFromDto(BookingRequestDto dto, @MappingTarget Booking booking);
}
