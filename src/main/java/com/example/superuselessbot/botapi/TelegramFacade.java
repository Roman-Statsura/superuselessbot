package com.example.superuselessbot.botapi;

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
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }
}
