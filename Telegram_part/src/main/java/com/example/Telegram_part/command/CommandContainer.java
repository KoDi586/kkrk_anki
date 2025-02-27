package com.example.Telegram_part.command;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandContainer {

    private final ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<>();

    public CommandContainer(
//            StartCommand startCommand,
                            ) {


//        commandMap.put(StartCommand.commandName, startCommand);

    }


    public void process(String commandName, Update update) {
        if (!commandMap.isEmpty()) {
            if (commandMap.containsKey(commandName)) {
                commandMap.get(commandName).execute(update);
            }
        }
    }
}
