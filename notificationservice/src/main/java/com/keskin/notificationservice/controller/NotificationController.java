package com.keskin.notificationservice.controller;

import com.keskin.notificationservice.constants.NotificationConstants;
import com.keskin.notificationservice.dto.ApiResponse;
import com.keskin.notificationservice.dto.NotificationContactInfoDto;
import com.keskin.notificationservice.dto.NotificationDto;
import com.keskin.notificationservice.service.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification Controller", description = "Endpoints for managing notifications")
public class NotificationController {

    private final INotificationService notificationService;
    private final NotificationContactInfoDto notificationContactInfoDto;

    @GetMapping("/test")
    @Operation(summary = "Test Notification Service", description = "Returns a test message after 5 seconds delay")
    public Mono<String> testNotification() {
        return Mono.delay(Duration.ofSeconds(5))
                .map(t -> "Notification service is working");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Notification by ID", description = "Fetch a single notification by its ID")
    public ResponseEntity<ApiResponse<NotificationDto>> getNotification(@PathVariable Long id) {
        NotificationDto notification = notificationService.getNotificationById(id);

        ApiResponse<NotificationDto> response = new ApiResponse<>(
                NotificationConstants.NOTIFICATION_FETCHED,
                NotificationConstants.HTTP_STATUS_200,
                notification
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All Notifications", description = "Fetch all notifications")
    public ResponseEntity<ApiResponse<List<NotificationDto>>> getAllNotifications() {
        List<NotificationDto> notifications = notificationService.getAllNotifications();

        ApiResponse<List<NotificationDto>> response = new ApiResponse<>(
                NotificationConstants.NOTIFICATION_FETCHED,
                NotificationConstants.HTTP_STATUS_200,
                notifications
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Notification", description = "Delete a notification by its ID")
    public ResponseEntity<ApiResponse<Boolean>> deleteNotification(@PathVariable Long id) {
        boolean deleted = notificationService.deleteNotification(id);

        ApiResponse<Boolean> response;

        if (deleted) {
            response = new ApiResponse<>(
                    NotificationConstants.NOTIFICATION_DELETED,
                    NotificationConstants.HTTP_STATUS_200,
                    null
            );
        } else {
            response = new ApiResponse<>(
                    NotificationConstants.NOTIFICATION_DELETE_FAILED,
                    NotificationConstants.HTTP_STATUS_417,
                    null
            );
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/contact-info")
    @Operation(summary = "Get Contact Info", description = "Fetch contact information for the notification service")
    public ResponseEntity<NotificationContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationContactInfoDto);
    }
}
