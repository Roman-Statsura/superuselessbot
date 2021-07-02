package com.example.superuselessbot.botapi.handlers;

import com.example.superuselessbot.botapi.BotState;
import com.example.superuselessbot.botapi.InputMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class UnsubscriptionHandler implements InputMessageHandler {
    @Override
    public SendMessage handle(Message message) {
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return null;
    }
}
