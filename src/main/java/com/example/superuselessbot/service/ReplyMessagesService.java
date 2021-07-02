package com.example.superuselessbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyMessagesService {
    private final MainMenuService mainMenuService;

    public ReplyMessagesService(MainMenuService mainMenuService) {
        this.mainMenuService = mainMenuService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage){
        SendMessage message = new SendMessage(chatId,replyMessage);
        //message.setReplyMarkup(mainMenuService.getMainMenuKeyboard());
        return message;
    }
}