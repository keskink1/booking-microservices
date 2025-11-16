package com.keskin.notificationservice.mapper;

import com.keskin.notificationservice.dto.NotificationDto;
import com.keskin.notificationservice.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto toDto(Notification notification);

    Notification toEntity(NotificationDto dto);

}