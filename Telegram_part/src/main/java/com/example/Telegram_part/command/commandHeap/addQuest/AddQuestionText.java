package com.example.Telegram_part.command.commandHeap.addQuest;

import com.example.Telegram_part.command.Command;
import com.example.Telegram_part.service.NotificationService;
import com.example.Telegram_part.service.UserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddQuestionText implements Command {

    public static String commandName = "/add_question_text";
    private final TelegramBot telegramBot;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public void execute(Update update) {

        String text = update.message().text();// конвертировать в str[]

        List<String> list = new ArrayList<>(
                List.of(text.split("\\|"))
        );

        Long chatId = update.message().chat().id();
        notificationService.saveByStrings(
                list,
                userService.getSecondsByChatId(chatId),
                chatId

        );
        // save notification

        int listSize = list.size();


        telegramBot.execute(new SendMessage(chatId,
                "Было сохранено " + listSize + " карточек"
                ));
        userService.updateSession(chatId, "NULL");
    }
}
