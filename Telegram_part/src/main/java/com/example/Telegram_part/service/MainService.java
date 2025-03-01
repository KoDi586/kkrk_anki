package com.example.Telegram_part.service;

import com.example.Telegram_part.command.CommandContainer;
import com.example.Telegram_part.model.UserModel;
import com.example.Telegram_part.repository.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final UserRepository userRepository;
    private final CommandContainer commandContainer;
    private final UserService userService;

//    public List<String> getUsers() {
//        List<String> result = new ArrayList<>();
//        for (UserModel userModel : userRepository.findAll()) {
//            result.add(userModel.toString());
//        }
//        return result;
//    }


    public void save(Update update) {
        UserModel userModel = null;
        try {
            userModel = userRepository.findByChatId(update.message().chat().id()).get();
        } catch (Exception e) {
            log.info("old user");
        }
        if (userModel == null) {
            userRepository.save(new UserModel(
                    userRepository.count()+1234,
                    update.message().chat().username(),
                    "null",
                    update.message().chat().id(),
                    20
            ));
        }
    }

    public void process(Update update) {
        save(update);
        commandContainer.process(update.message().text(), update);
    }

    public void workWithText(Update update) {
        Long chatId = update.message().chat().id();
        UserModel userModel = userRepository.findByChatId(chatId).get();
        commandContainer.process(userService.receive(userModel.getSession()),update);
    }
}
