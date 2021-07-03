package com.example.superuselessbot.botapi.handlers.sub;

import com.example.superuselessbot.botapi.BotState;
import com.example.superuselessbot.botapi.InputMessageHandler;
import com.example.superuselessbot.buttons.CryptoButtons;
import com.example.superuselessbot.cache.UserDataCache;
import com.example.superuselessbot.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class SubscriptionHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;
    private final CryptoButtons cryptoButtons;

    public SubscriptionHandler(UserDataCache userDataCache, ReplyMessagesService messagesService, CryptoButtons cryptoButtons) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
        this.cryptoButtons = cryptoButtons;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SUBSCRIPTION;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId,"Выберите название криптовалюты, " +
                "на рассылку о стоимости которой вы хотите подписаться: ");
        replyToUser.setReplyMarkup(cryptoButtons.getInlineMessageButtons());
        userDataCache.setUsersCurrentBotState(userId,BotState.EXPECT_CRYPTO_SUB);

        return replyToUser;
    }
}
