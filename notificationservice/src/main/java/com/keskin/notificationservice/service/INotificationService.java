package com.keskin.notificationservice.service;

import com.keskin.notificationservice.dto.BookingMsgDto;
import com.keskin.notificationservice.dto.NotificationDto;

import java.util.List;

public interface INotificationService {

    NotificationDto createNotification(BookingMsgDto bookingMsgDto);

    NotificationDto getNotificationById(Long id);

    List<NotificationDto> getAllNotifications();

    boolean deleteNotification(Long id);
}
