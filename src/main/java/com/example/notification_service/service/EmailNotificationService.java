package com.example.notification_service.service;

import com.example.notification_service.NotificationCollection;
import com.example.notification_service.constant.Status;
import com.example.notification_service.pojo.Notification;
import com.example.notification_service.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailNotificationService implements NotificationService{

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    @Autowired
    NotificationRepo notificationRepo;
    @Override
    public String sendNotification(Notification notifcation, String recipientEmail) {
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(recipientEmail);
            mailMessage.setText(notifcation.getCurrentPrice()+ " "+ notifcation.getHigh()+" "+notifcation.getVolume()+" "+notifcation.getMarketCap());
            mailMessage.setSubject("BTC details for the day");

            notificationRepo.save(
                    com.example.notification_service.NotificationCollection.builder()
                            .notification(notifcation)
                            .recipientEmail(recipientEmail)
                            .createdDate(new java.util.Date())
                            .updatedDate(new java.util.Date())
                            .status(com.example.notification_service.constant.Status.SENT)
                            .build()
            );
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            notificationRepo.save(
                    com.example.notification_service.NotificationCollection.builder()
                            .notification(notifcation)
                            .recipientEmail(recipientEmail)
                            .createdDate(new java.util.Date())
                            .updatedDate(new java.util.Date())
                            .status(Status.FAILED)
                            .build()
            );
            return "Error while Sending Mail";
        }
    }

    @Override
    public List<Notification> getNotifications(String recipientEmail) {
        return notificationRepo.findByRecipientEmail(recipientEmail).stream().map(
                NotificationCollection::getNotification
        ).toList();
    }

    @Override
    public void deleteNotification(String recipientEmail) {
        notificationRepo.deleteByRecipientEmail(recipientEmail);
    }


}
