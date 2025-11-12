package com.keskin.notificationservice.service;

import com.keskin.notificationservice.dto.CreateNotificationRequestDto;
import com.keskin.notificationservice.dto.NotificationDto;

import java.util.List;

public interface INotificationService {
    NotificationDto createNotification(CreateNotificationRequestDto requestDto);

    NotificationDto getNotificationById(Long id);

    List<NotificationDto> getAllNotifications();

    boolean deleteNotification(Long id);
}
