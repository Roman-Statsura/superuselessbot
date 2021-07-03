package com.example.superuselessbot.botapi.handlers.luxsub;

import com.example.superuselessbot.botapi.BotState;
import com.example.superuselessbot.botapi.InputMessageHandler;
import com.example.superuselessbot.cache.UserDataCache;
import com.example.superuselessbot.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class LuxurySubscriptionHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;

    public LuxurySubscriptionHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.LUXURY_SUBSCRIPTION;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        return messagesService.getReplyMessage(chatId,"Заглушка");
    }
}
