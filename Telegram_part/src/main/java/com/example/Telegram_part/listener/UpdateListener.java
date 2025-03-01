package com.example.Telegram_part.listener;

import com.example.Telegram_part.service.MainService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.example.sevrice.listenerService.MainListenerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final MainService mainService;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            try {
                log.info("profile first name is: {}, username is: {}",
                        update.message().chat().firstName(), update.message().chat().username());

//                String savingResult = mainService.save(update);
                if (update.message().text().startsWith("/")) {
                    mainService.process(update);
                } else {
                    mainService.workWithText(update);
                }
            } catch (Exception e) {
                log.warn("will error in {}", update.toString());
            }
//            if (update.message() != null) {
//                if (update.message().text() != null) {
//                    listenerService.workWithText(
//                            update.message().text(),
//                            update
//                    );
//                } else {
//                    listenerService.dontUnderstand(
//                            update.message().chat().id()
//                    );
//                }
//            } else if (update.callbackQuery() != null) {
//                listenerService.workWithButton(
//                        update
//                );
//            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
