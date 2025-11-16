package com.keskin.message.functions;

import com.keskin.message.dto.BookingMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<BookingMsgDto, BookingMsgDto> email() {
        return bookingMsgDto -> {
            log.info("Sending email for booking: {}", bookingMsgDto.id() + bookingMsgDto.email());

            return bookingMsgDto;
        };
    }

}
