package com.example.Telegram_part.command;

import com.example.Telegram_part.command.commandHeap.AllLearningCommand;
import com.example.Telegram_part.command.commandHeap.addQuest.AddQuestion;
import com.example.Telegram_part.command.commandHeap.StartCommand;
import com.example.Telegram_part.command.commandHeap.addQuest.AddQuestionSeconds;
import com.example.Telegram_part.command.commandHeap.addQuest.AddQuestionText;
import com.example.Telegram_part.command.commandHeap.learnWordTraining.EnWordTraining;
import com.example.Telegram_part.command.commandHeap.learnWordTraining.EnWordTrainingAddToDB;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandContainer {

    private final ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<>();

    public CommandContainer(
            StartCommand startCommand,
            AddQuestion addQuestion,
            AddQuestionSeconds addQuestionSeconds,
            AddQuestionText addQuestionText,
            EnWordTraining enWordTraining,
            EnWordTrainingAddToDB enWordTrainingAddToDB,
            AllLearningCommand allLearningCommand
                            ) {


        commandMap.put(StartCommand.commandName, startCommand);
        commandMap.put(AddQuestion.commandName, addQuestion);
        commandMap.put(AddQuestionSeconds.commandName, addQuestionSeconds);
        commandMap.put(AddQuestionText.commandName, addQuestionText);
        commandMap.put(EnWordTraining.commandName, enWordTraining);
        commandMap.put(EnWordTrainingAddToDB.commandName, enWordTrainingAddToDB);
        commandMap.put(AllLearningCommand.commandName, allLearningCommand);

    }


    public void process(String commandName, Update update) throws IOException, InterruptedException {
        if (!commandMap.isEmpty()) {
            if (commandMap.containsKey(commandName)) {
                commandMap.get(commandName).execute(update);
            }
        }
    }
}
