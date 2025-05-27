package com.example.notification_service;

import com.example.notification_service.constant.Status;
import com.example.notification_service.pojo.Notification;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "notifications")
public class NotificationCollection {
    @Id
    private  String id;
    private String recipientEmail;
    private Notification notification;
    private Date createdDate;
    private Date updatedDate;
    private Status status;
}
