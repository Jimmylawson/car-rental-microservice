package com.jimmydev.message.functions;


import com.jimmydev.message.dto.BookingMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Function;

@Configuration
public class MessageFunctions {
    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<BookingMsgDto, BookingMsgDto> email(){
        return bookingMsgDto -> {
            System.out.println("Sending email with details: " + bookingMsgDto.toString());
            log.info("Sending email with details {}", bookingMsgDto.toString());
            return bookingMsgDto;
        };

    }

    @Bean
    public Function<BookingMsgDto, UUID> sms(){
        return bookingMsgDto->{
            log.info("Sending sms with details: " + bookingMsgDto.toString());
            return bookingMsgDto.bookingId();
        };

    }

}
