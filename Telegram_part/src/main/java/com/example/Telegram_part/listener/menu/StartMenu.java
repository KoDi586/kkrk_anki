package com.example.Telegram_part.listener.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class StartMenu implements MenuPattern{

    private TelegramBot telegramBot;

    @Override
    public void SendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("Все игры")
                .callbackData("/allLearning");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Ничего")
                .callbackData("/null");
        Keyboard keyboard = new InlineKeyboardMarkup(new InlineKeyboardButton[]{button1, button2});
        SendResponse response = telegramBot.execute(
                new SendMessage(chatId, "Выберите интересующий вариант:").replyMarkup(keyboard));
    }

}
