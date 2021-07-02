package com.example.superuselessbot.botapi.handlers;

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
public class CryptocurrencyHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;
    private final CryptoButtons cryptoButtons;

    public CryptocurrencyHandler(UserDataCache userDataCache, ReplyMessagesService messagesService, CryptoButtons cryptoButtons) {
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
        return BotState.CRYPTOCURRENCY;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId,"Введите название" +
                " криптовалюты(ее аббревиатуру) или выберите из популярных");
        replyToUser.setReplyMarkup(cryptoButtons.getInlineMessageButtons());
        userDataCache.setUsersCurrentBotState(userId,BotState.FIND_PRICE);

        return replyToUser;
    }

}
