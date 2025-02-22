package com.example.Telegram_part.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @Column(name = "user_id")
    private long userId;
    private String username;
    @Column(name = "chat_id")
    private long chatId;

}
