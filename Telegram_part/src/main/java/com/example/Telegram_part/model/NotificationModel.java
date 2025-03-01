package com.example.Telegram_part.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {

    @Id
    @Column(name = "notification_id")
    private long notificationId;
    private String text;
    private LocalDateTime datetime;
    @Column(name = "user_chat_id")
    private long userChatId;

}
