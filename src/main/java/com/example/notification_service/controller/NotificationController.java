package com.example.notification_service.controller;

import com.example.notification_service.pojo.Notification;
import com.example.notification_service.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/notification")
public class NotificationController {

    @Autowired
    EmailNotificationService emailNotificationService;
    @PostMapping("/send/{recipientEmail}")
    public String sendNotification(@RequestBody Notification notification, @PathVariable String recipientEmail) {
        emailNotificationService.sendNotification(notification, recipientEmail);
        return "Notification sent successfully to " + recipientEmail;
    }

    @GetMapping("/get/{recipientEmail}")
    public List<Notification> getNotification(@PathVariable String recipientEmail) {

        return emailNotificationService.getNotifications(recipientEmail);
    }

    @DeleteMapping("/delete/{recipientEmail}")
    public void deleteNotification(@PathVariable String recipientEmail) {

         emailNotificationService.getNotifications(recipientEmail);
    }
}
