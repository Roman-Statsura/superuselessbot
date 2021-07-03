package com.example.superuselessbot.botapi.handlers.unsub;

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
public class UnsubscriptionHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;
    private final CryptoButtons cryptoButtons;

    public UnsubscriptionHandler(UserDataCache userDataCache, ReplyMessagesService messagesService, CryptoButtons cryptoButtons) {
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
        return BotState.UNSUBSCRIBE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        var subSet = userDataCache.getSubByUser(userId);
        SendMessage replyToUser;
        if (userDataCache.isUserANew(userId) || userDataCache.getSubByUser(userId).isEmpty()){
            userDataCache.setUsersCurrentBotState(userId,BotState.MENU);
            return messagesService.getReplyMessage(chatId,"У вас нет активных подписок.");
        }
        else {
            var s = String.join(",", subSet);
            replyToUser = messagesService.getReplyMessage(chatId, "Выберите название криптовалюты, " +
                    "от рассылки на стоимость которой хотите отписаться. Вы подписаны на: " + s);
            userDataCache.setUsersCurrentBotState(userId, BotState.EXPECT_CRYPTO_UNSUB);
            replyToUser.setReplyMarkup(cryptoButtons.getInlineMessageButtonsOptional(subSet));

            return replyToUser;
        }
    }
}
