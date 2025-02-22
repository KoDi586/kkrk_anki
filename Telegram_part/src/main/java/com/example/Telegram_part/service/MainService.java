package com.example.Telegram_part.service;

import com.example.Telegram_part.model.UserModel;
import com.example.Telegram_part.repository.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final UserRepository userRepository;

    public List<String> getUsers() {
        List<String> result = new ArrayList<>();
        for (UserModel userModel : userRepository.findAll()) {
            result.add(userModel.toString());
        }
        return result;
    }

    public String save(Update update) {
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
                    update.message().chat().id()
            ));
            return "saving complete";
        }
        return "saving fail";
    }

}
