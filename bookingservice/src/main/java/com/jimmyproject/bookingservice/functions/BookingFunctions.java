package com.jimmyproject.bookingservice.functions;

import com.jimmyproject.bookingservice.dtos.BookingMsgDto;
import com.jimmyproject.bookingservice.service.BookingServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class BookingFunctions {
    @Bean
    public Consumer<UUID> updatedCommunication(BookingServiceInterface bookingService) {
        {
            return bookingId -> {
                log.info("Updating communication for booking with ID: {}", bookingId);
                bookingService.updateCommunicationStatus(bookingId);
            };

        }

    }

}

