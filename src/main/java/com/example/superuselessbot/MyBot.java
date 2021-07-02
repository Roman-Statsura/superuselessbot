package com.example.superuselessbot;

import com.example.superuselessbot.botapi.TelegramFacade;
import lombok.Setter;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Setter
public class MyBot extends TelegramWebhookBot {
    private String webHookPath;
    private String token;
    private String userName;

    private TelegramFacade telegramFacade;

    public MyBot(TelegramFacade telegramFacade){
        this.telegramFacade = telegramFacade;
    }

    @SneakyThrows
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        return telegramFacade.handleUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
