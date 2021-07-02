package com.example.superuselessbot.botapi.handlers;

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
public class HelpHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;

    public HelpHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        userDataCache.setUsersCurrentBotState(userId,BotState.MENU);
        return messagesService.getReplyMessage(chatId,"help:\n" +
                "cryptocurrency – запрос стоимости криптовалюты\n" +
                "subscribe – подписка на рассылку о стоимости криптовалюты\n" +
                "unsubscribe – отписка от рассылки о стоимости криптовалюты\n" +
                "luxurySubscribe – подписка на рассылку об изменении стоимости криптовалюты на заданный процент");
    }
}
