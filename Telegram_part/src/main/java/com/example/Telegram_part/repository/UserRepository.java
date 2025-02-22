package com.example.Telegram_part.repository;

import com.example.Telegram_part.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByChatId(long chatId);
}
