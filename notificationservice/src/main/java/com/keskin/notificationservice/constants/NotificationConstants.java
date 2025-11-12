package com.keskin.notificationservice.constants;

public class NotificationConstants {

    private NotificationConstants() {
        // Prevent instantiation
    }

    public static final String NOTIFICATION_CREATED = "Notification created successfully";
    public static final String NOTIFICATION_FETCHED = "Notification fetched successfully";
    public static final String NOTIFICATION_DELETED = "Notification deleted successfully";
    public static final String NOTIFICATION_UPDATED = "Notification updated successfully";
    public static final String NOTIFICATION_DELETE_FAILED = "Notification delete failed";
    public static final String NOTIFICATION_UPDATE_FAILED = "Notification update failed";
    public static final String NOTIFICATION_NOT_FOUND = "Notification not found";

    public static final String HTTP_STATUS_200 = "OK";
    public static final String HTTP_STATUS_201 = "Created";
    public static final String HTTP_STATUS_204 = "No Content";
    public static final String HTTP_STATUS_404 = "Not Found";
    public static final String HTTP_STATUS_417 = "Operation Failed";
    public static final String HTTP_STATUS_500 = "Internal Server Error";
}
