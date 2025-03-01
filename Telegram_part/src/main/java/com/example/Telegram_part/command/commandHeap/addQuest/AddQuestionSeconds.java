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
public class AddQuestionSeconds implements Command {

    public static String commandName = "/add_question_seconds";
    private final TelegramBot telegramBot;
    private final UserService userService;

    @Override
    public void execute(Update update) {

        String text = update.message().text();
        //text formatted
        Long seconds = Long.parseLong(text);

        userService.updateSeconds(update.message().chat().id(), seconds);

        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Введите notifications, которые нужно выдавать, разлеляя их знаком |"
                ));
        userService.updateSession(update.message().chat().id(), "ADD_QUESTION_SECONDS");
    }
}
