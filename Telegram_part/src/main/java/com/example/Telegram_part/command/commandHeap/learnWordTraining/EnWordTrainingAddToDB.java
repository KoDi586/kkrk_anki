package com.example.Telegram_part.command.commandHeap.learnWordTraining;

import com.example.Telegram_part.command.Command;
import com.example.Telegram_part.service.UserService;
import com.example.Telegram_part.test.MyMain;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class EnWordTrainingAddToDB implements Command {

    public static String commandName = "/add_words_for_learning_to_db";
    private static String ToChat = "I know Russian, and I need these exercises." +
            " so that I can go through them using anki and memorize them effectively. " +
            "separate each exercise in English with a \"|\" sign so that I separate them " +
            "correctly. There are different types of tasks and several for each word. " +
            "don't write too much (introductory words, closing words) " +
            "in the format: task | task | task ... , where task is not just a word, " +
            "for words: ";

    private static String ToChat2 = "write tasks for learning English words. " +
            "do not write unnecessary (introductory words, concluding)" +
            " task in Russian" +
            " in the format: task | task | task ... , where the task is not just a word, for words: ";

    private final TelegramBot telegramBot;
    private final UserService userService;

    @Override
    public void execute(Update update) throws IOException, InterruptedException {
        String words = update.message().text();
        telegramBot.execute(new SendMessage(update.message().chat().id(),
                "Генерация упражнений началась, ожидайте..."));
        MyMain.startDeepSeek();
        try {
            String response1 = MyMain.runDeepSeek(ToChat + words);
            telegramBot.execute(new SendMessage(update.message().chat().id(), response1));
        } catch (Exception e) {
            telegramBot.execute(new SendMessage(update.message().chat().id(), "Случилась ошибка"));
        } finally {
            MyMain.stopDeepSeek();
        }
        userService.updateSession(update.message().chat().id(), "NULL");
    }
}
