package com.example.superuselessbot.botapi.handlers.unsub;

import com.example.superuselessbot.botapi.BotState;
import com.example.superuselessbot.botapi.InputMessageHandler;
import com.example.superuselessbot.cache.UserDataCache;
import com.example.superuselessbot.service.CheckCrypto;
import com.example.superuselessbot.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Locale;

@Slf4j
@Component
public class ExpectCryptoToUnsubHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;

    public ExpectCryptoToUnsubHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.EXPECT_CRYPTO_UNSUB;
    }
    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        var crypto = inputMsg.getText().toUpperCase(Locale.ROOT);

        if (CheckCrypto.checkCrypto(crypto)){
            if (!userDataCache.getSubByUser(userId).contains(crypto)){
                return messagesService.getReplyMessage(chatId,"Вы не подписаны на данную криптовалюту!");
            }
            userDataCache.deleteUserSub(userId, crypto);

            return messagesService.getReplyMessage(chatId,"Вы успешно отписаны от рассылки на стоимость "+crypto+" !");
        }
        else
            return messagesService.getReplyMessage(chatId,"Данная криптовалюта не найдена!");
    }
}
