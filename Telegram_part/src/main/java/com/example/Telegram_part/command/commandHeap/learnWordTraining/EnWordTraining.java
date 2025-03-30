package com.example.Telegram_part.command.commandHeap.learnWordTraining;

import com.example.Telegram_part.command.Command;
import com.example.Telegram_part.service.UserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnWordTraining implements Command {

    public static String commandName = "/en_words_training";
    private final TelegramBot telegramBot;
    private final UserService userService;

    @Override
    public void execute(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), """
                чтобы потренить слова напишите их. Жду ваш ответ по типу: "first, second..., latest.
                """));
        userService.updateSession(update.message().chat().id(), "WAIT_WORDS");
    }
}
