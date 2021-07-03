package com.example.superuselessbot;

import com.example.superuselessbot.api.Api;
import com.example.superuselessbot.botapi.TelegramFacade;
import com.example.superuselessbot.cache.UserDataCache;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;

@Setter
public class MyBot extends TelegramWebhookBot {
    private String webHookPath;
    private String token;
    private String userName;

    private UserDataCache userDataCache;

    private TelegramFacade telegramFacade;

    public MyBot(TelegramFacade telegramFacade,UserDataCache userDataCache){
        this.userDataCache = userDataCache;
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

    @Scheduled(fixedRate = 3000)
    public void method() throws IOException, URISyntaxException, TelegramApiException {
        var map = userDataCache.getSubscriptions();
        for (var item : map.entrySet()){
            for (var x : item.getValue()){
                System.out.println(x);
                execute(new SendMessage((long)item.getKey(),Api.getCrypto(x)));
            }
        }
    }
}
