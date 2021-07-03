package com.example.superuselessbot.controller;

import com.example.superuselessbot.MyBot;
import com.example.superuselessbot.model.Crypto;
import com.example.superuselessbot.model.Trigger;
import com.example.superuselessbot.service.ReplyMessagesService;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
public class WebHookController {
    private final MyBot myBot;
    private final ReplyMessagesService messagesService;

    public WebHookController(MyBot myBot, ReplyMessagesService messagesService) {
        this.myBot = myBot;
        this.messagesService = messagesService;
    }

    @PostMapping(value = "/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return myBot.onWebhookUpdateReceived(update);
    }
    @GetMapping(value = "/api")
    public String on(){
        return "12345";
    }

    @GetMapping(value = "/api1",produces = MediaType.APPLICATION_JSON_VALUE)
    public Trigger getTrigger(){
        return new Trigger("BTC");
    }

    @PostMapping(value = "api/crypto",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void onUpdateCrypto(@RequestBody Crypto crypto) throws TelegramApiException {
        myBot.execute(new SendMessage((long)crypto.getId(),"Стоимость "+crypto.getFigi()+" равна "
                +crypto.getPrice() +" USD"));
    }
}
