package com.example.Telegram_part.command;

import com.pengrad.telegrambot.model.Update;

import java.io.IOException;

public interface Command {
    void execute(Update update) throws IOException, InterruptedException;
}
