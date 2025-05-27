package com.example.notification_service.repository;

import com.example.notification_service.NotificationCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepo extends MongoRepository<NotificationCollection, String> {
    List<NotificationCollection> findByRecipientEmail(String recipientEmail);

    void deleteByRecipientEmail(String recipientEmail);
}
