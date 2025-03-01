package com.example.Telegram_part.service;

import com.example.Telegram_part.model.NotificationModel;
import com.example.Telegram_part.repository.NotificationRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final NotificationRepository repository;
    private final TelegramBot telegramBot;

    @Scheduled(cron = "0/10 * * * * *") // Каждую секунду
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now(); // Время в текущем часовом поясе JVM
        LocalDateTime windowEnd = now.minusSeconds(10); // Окно в 10 sec

        List<NotificationModel> notifications = repository.findNotificationsInTimeWindow(windowEnd, now);
//        List<NotificationModel> notifications = repository.findNotificationsInTimeWindow();
        for (NotificationModel notificationModel : notifications) {
            telegramBot.execute(new SendMessage(
                    notificationModel.getUserChatId(),
                    "Напоминание #" + notificationModel.getNotificationId() + " в " +
                            notificationModel.getDatetime() + ": " + notificationModel.getText()
            ));
//            System.out.println("Напоминание #" + notificationModel.getNotificationId() + " в " +
//                    notificationModel.getDatetime() + ": " + notificationModel.getText());
            // Тут можно добавить логику обработки (уведомления, удаление и т.д.)
        }
    }

}
