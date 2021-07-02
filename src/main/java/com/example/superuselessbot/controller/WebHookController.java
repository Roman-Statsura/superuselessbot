package com.example.superuselessbot.controller;

import com.example.superuselessbot.MyBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final MyBot myBot;

    public WebHookController(MyBot myBot) {
        this.myBot = myBot;
    }

    @PostMapping(value = "/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return myBot.onWebhookUpdateReceived(update);
    }
}
