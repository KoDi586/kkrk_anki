package com.example.Telegram_part.service;

import com.example.Telegram_part.model.UserModel;
import com.example.Telegram_part.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void updateSession(long chatId, String session) {
        UserModel userModel = userRepository.findByChatId(chatId).get();
        userModel.setSession(session);
        userRepository.save(userModel);
    }

    public void updateSeconds(Long chatId, Long seconds) {
        UserModel userModel = userRepository.findByChatId(chatId).get();
        userModel.setSeconds(seconds);
        userRepository.save(userModel);
    }


    public long getSecondsByChatId(Long chatId) {
        UserModel userModel = userRepository.findByChatId(chatId).get();
        return userModel.getSeconds();
    }

    public String receive(String session) {
        return Session.valueOf(session).getValue();
    }

    public enum Session {
        ADD_QUESTION("/add_question_seconds"),
        ADD_QUESTION_SECONDS("/add_question_text"),
        STOP("/stop");

        private final String value;

        Session(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
