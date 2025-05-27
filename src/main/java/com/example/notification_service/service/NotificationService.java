package com.example.notification_service.service;

import com.example.notification_service.pojo.Notification;

import java.util.List;

public interface NotificationService {
    public String sendNotification(Notification notifcation, String recipientEmail);
    public List<Notification> getNotifications(String recipientEmail);

    public void deleteNotification(String recipientEmail);
}
