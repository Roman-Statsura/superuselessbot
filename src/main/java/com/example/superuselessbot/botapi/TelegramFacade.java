package com.example.superuselessbot.botapi;

import com.example.superuselessbot.api.Api;
import com.example.superuselessbot.cache.UserDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
@Slf4j
public class TelegramFacade {
    private final BotStateContext botStateContext;
    private final UserDataCache userDataCache;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
    }

    public BotApiMethod<?> handleUpdate(Update update) throws IOException, URISyntaxException {
        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return processCallbackQuery(callbackQuery);
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }


    private SendMessage handleInputMessage(Message message) throws IOException, URISyntaxException {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg){
            case "/start":
                botState = BotState.NEW;
                break;
            case "help":
                botState = BotState.HELP;
                break;
            case "setting":
                botState = BotState.SETTING;
                break;
            case "subscribe":
                botState = BotState.SUBSCRIPTION;
                break;
            case "luxurySubscribe":
                botState = BotState.LUXURY_SUBSCRIPTION;
                break;
            case "unsubscribe":
                botState = BotState.UNSUBSCRIBE;
                break;
            case "cryptocurrency":
                botState = BotState.CRYPTOCURRENCY;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }
    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();
        BotApiMethod<?> callBackAnswer = null;

        if (userDataCache.getUsersCurrentBotState(userId)==BotState.FIND_PRICE){
            try {
                callBackAnswer =new SendMessage(chatId, Api.getCrypto(buttonQuery.getData()));
                userDataCache.setUsersCurrentBotState(userId,BotState.FIND_PRICE);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        if (userDataCache.getUsersCurrentBotState(userId)==BotState.EXPECT_CRYPTO_SUB){
            callBackAnswer = new SendMessage(chatId,"Тоже заглушка");
        }

        return callBackAnswer;
    }
}
