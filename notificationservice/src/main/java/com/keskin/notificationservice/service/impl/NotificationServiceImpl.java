package com.keskin.notificationservice.service.impl;

import com.keskin.notificationservice.dto.BookingMsgDto;
import com.keskin.notificationservice.dto.NotificationDto;
import com.keskin.notificationservice.entity.Notification;
import com.keskin.notificationservice.exceptions.ResourceNotFoundException;
import com.keskin.notificationservice.repository.NotificationRepository;
import com.keskin.notificationservice.service.INotificationService;
import com.keskin.notificationservice.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
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
    public NotificationDto createNotification(BookingMsgDto bookingMsgDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = bookingMsgDto.appointmentTime().format(formatter);

        Notification notification = Notification.builder()
                .bookingId(bookingMsgDto.id())
                .userName(bookingMsgDto.userName())
                .message("Booking created for " + bookingMsgDto.email() +
                        " at " + formattedDate)
                .status(bookingMsgDto.status())
                .build();

        Notification savedNotification = notificationRepository.save(notification);

        NotificationDto notificationDto = new NotificationDto(
                savedNotification.getId(),
                savedNotification.getBookingId(),
                savedNotification.getUserName(),
                savedNotification.getMessage(),
                savedNotification.getStatus(),
                savedNotification.getCreatedAt()
        );

        return notificationDto;
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
