package com.example.Telegram_part.listener.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AllLearningMenu implements MenuPattern {

    private TelegramBot telegramBot;

    @Override
    public void SendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("command1")
                .callbackData("/null");
        InlineKeyboardButton button2 = new InlineKeyboardButton("command2")
                .callbackData("/null");
        Keyboard keyboard = new InlineKeyboardMarkup(new InlineKeyboardButton[]{button1, button2});
        SendResponse response = telegramBot.execute(
                new SendMessage(chatId, "Выберите интересующий Вас приют:").replyMarkup(keyboard));
    }
}
