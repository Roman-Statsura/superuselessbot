package com.example.superuselessbot.botapi.handlers;

import com.example.superuselessbot.api.Api;
import com.example.superuselessbot.botapi.BotState;
import com.example.superuselessbot.botapi.InputMessageHandler;
import com.example.superuselessbot.cache.UserDataCache;
import com.example.superuselessbot.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Slf4j
public class FindPriceHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;

    public FindPriceHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FIND_PRICE;
    }

    private SendMessage processUsersInput(Message inputMsg){
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = null;

        try {
            replyToUser = messagesService.getReplyMessage(chatId, Api.getCrypto(inputMsg.getText()));
        }catch (Exception e){
            e.printStackTrace();
        }

        return replyToUser;
    }
}