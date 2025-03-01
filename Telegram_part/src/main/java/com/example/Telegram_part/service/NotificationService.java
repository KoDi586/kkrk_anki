package com.example.Telegram_part.service;

import com.example.Telegram_part.model.NotificationModel;
import com.example.Telegram_part.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void saveByStrings(List<String> texts, long seconds, long chatId) {
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0; i < texts.size(); i++) {
//            dateTime = dateTime.plusSeconds(seconds*(i+1L));
            notificationRepository.save(
                    new NotificationModel(
                            notificationRepository.count()+1L,
                            texts.get(i),
                            dateTime.plusSeconds(seconds*(i+1L)),
                            chatId
                    )
            );
        }
    }
}
