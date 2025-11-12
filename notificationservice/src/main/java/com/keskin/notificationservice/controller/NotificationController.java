package com.keskin.notificationservice.controller;

import com.keskin.notificationservice.constants.NotificationConstants;
import com.keskin.notificationservice.dto.ApiResponse;
import com.keskin.notificationservice.dto.CreateNotificationRequestDto;
import com.keskin.notificationservice.dto.NotificationContactInfoDto;
import com.keskin.notificationservice.dto.NotificationDto;
import com.keskin.notificationservice.service.INotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final INotificationService notificationService;
    private final NotificationContactInfoDto notificationContactInfoDto;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<NotificationDto>> createNotification(
            @Valid @RequestBody CreateNotificationRequestDto requestDto,
            UriComponentsBuilder uriBuilder
    ) {
        NotificationDto created = notificationService.createNotification(requestDto);

        URI location = uriBuilder
                .path("/api/notifications/{id}")
                .buildAndExpand(created.id())
                .toUri();

        ApiResponse<NotificationDto> response = new ApiResponse<>(
                NotificationConstants.NOTIFICATION_CREATED,
                NotificationConstants.HTTP_STATUS_201,
                created
        );

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<NotificationContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationContactInfoDto);
    }
}
