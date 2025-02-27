package com.example.Telegram_part.command;

import com.pengrad.telegrambot.model.Update;

public interface Command {
    void execute(Update update);
}
