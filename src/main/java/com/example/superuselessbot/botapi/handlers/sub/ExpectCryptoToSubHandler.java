package com.example.superuselessbot.botapi.handlers.sub;

import com.example.superuselessbot.botapi.BotState;
import com.example.superuselessbot.botapi.InputMessageHandler;
import com.example.superuselessbot.cache.UserDataCache;
import com.example.superuselessbot.model.Trigger;
import com.example.superuselessbot.repository.RedisRepositoryImpl;
import com.example.superuselessbot.repository.TriggerRepository;
import com.example.superuselessbot.service.CheckCrypto;
import com.example.superuselessbot.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Locale;

@Slf4j
@Component
public class ExpectCryptoToSubHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;
    private final RedisRepositoryImpl redisRepository;
    private final TriggerRepository triggerRepository;

    public ExpectCryptoToSubHandler(UserDataCache userDataCache, ReplyMessagesService messagesService, RedisRepositoryImpl redisRepository, TriggerRepository triggerRepository) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
        this.redisRepository = redisRepository;
        this.triggerRepository = triggerRepository;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.EXPECT_CRYPTO_SUB;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        var crypto = inputMsg.getText().toUpperCase(Locale.ROOT);

        if (CheckCrypto.checkCrypto(crypto)){
            userDataCache.addUserSub(userId,crypto);
            return messagesService.getReplyMessage(chatId,"Вы успешно подписаны на рассылку о стоимости "+crypto+" !");
        }
        else
            return messagesService.getReplyMessage(chatId,"Данная криптовалюта не найдена!");
    }
}
