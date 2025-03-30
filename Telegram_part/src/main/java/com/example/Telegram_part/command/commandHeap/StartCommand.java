package com.example.Telegram_part.command.commandHeap;

import com.example.Telegram_part.command.Command;
import com.example.Telegram_part.listener.menu.StartMenu;
import com.example.Telegram_part.service.UserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {

    public static String commandName = "/start";
    private final TelegramBot telegramBot;
    private final UserService userService;
    private final StartMenu startMenu;

    @Override
    public void execute(Update update) {
        telegramBot.execute(new SendMessage(update.message().chat().id(), """
                всем привет вас приветствует ваш
                персональный жорен бот!
                
                Если вы хотите добавить вопросы то нажмите /add_question
                Если хотите составить упражнения для слов английского языка нажмите /en_words_training
               
                """));
        userService.updateSession(update.message().chat().id(), "START");
        startMenu.SendMenuMessage(update.message().chat().id());

    }
}
