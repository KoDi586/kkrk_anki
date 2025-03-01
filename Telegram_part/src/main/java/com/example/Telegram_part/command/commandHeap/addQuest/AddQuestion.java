package com.example.Telegram_part.command.commandHeap.addQuest;

import com.example.Telegram_part.command.Command;
import com.example.Telegram_part.service.UserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddQuestion implements Command {

    public static String commandName = "/add_question";
    private final TelegramBot telegramBot;
    private final UserService userService;

    @Override
    public void execute(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Введите время через которое выдавать новые tasks в " +
                        "формате 12:34:56, где 12 - часы, 34 минуты, 56 секунды"
                ));
        userService.updateSession(update.message().chat().id(), "ADD_QUESTION");
    }
}
