package com.keskin.notificationservice.functions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keskin.notificationservice.dto.BookingMsgDto;
import com.keskin.notificationservice.service.INotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class NotificationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(NotificationConfiguration.class);

    @Bean
    public Consumer<String> notificationProcessor(INotificationService notificationService, ObjectMapper objectMapper) {
        return bookingDetailsJson -> {
            try {
                BookingMsgDto bookingMsgDto = objectMapper.readValue(bookingDetailsJson, BookingMsgDto.class);
                notificationService.createNotification(bookingMsgDto);
                log.info("Notification created for booking id: {}", bookingMsgDto.id());
            } catch (Exception e) {
                log.error("Failed to process booking notification", e);
            }
        };
    }

}
