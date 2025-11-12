package com.keskin.notificationservice.service.impl;

import com.keskin.notificationservice.dto.CreateNotificationRequestDto;
import com.keskin.notificationservice.dto.NotificationDto;
import com.keskin.notificationservice.entity.Notification;
import com.keskin.notificationservice.entity.NotificationStatus;
import com.keskin.notificationservice.exceptions.ResourceNotFoundException;
import com.keskin.notificationservice.repository.NotificationRepository;
import com.keskin.notificationservice.service.INotificationService;
import com.keskin.notificationservice.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public NotificationDto createNotification(CreateNotificationRequestDto requestDto) {
        Notification notification = notificationMapper.createRequestToEntity(requestDto);
        Notification saved = notificationRepository.save(notification);
        return notificationMapper.toDto(saved);
    }

    @Override
    public NotificationDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID", "Notification", id.toString()));
        return notificationMapper.toDto(notification);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteNotification(Long id) {
        Optional<Notification> existing = notificationRepository.findById(id);
        if (existing.isEmpty()) return false;

        notificationRepository.deleteById(id);
        return true;
    }
}
