package com.example.superuselessbot.botapi.handlers;

import com.example.superuselessbot.botapi.BotState;
import com.example.superuselessbot.botapi.InputMessageHandler;
import com.example.superuselessbot.cache.UserDataCache;
import com.example.superuselessbot.service.MainMenuService;
import com.example.superuselessbot.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class NewHandler implements InputMessageHandler {
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;
    private final MainMenuService mainMenuService;

    public NewHandler(UserDataCache userDataCache, ReplyMessagesService messagesService, MainMenuService mainMenuService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
        this.mainMenuService = mainMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.NEW;
    }
    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId,"Доброго времени суток, "
                + inputMsg.getFrom().getFirstName() + "! "+
                "Я - чат-бот Cryptoinformer Хомяк Знает.  С удовольствием  помогу вам отслеживать курс 6 наиболее популярных" +
                " криптовалют: BTC (Биткойн), ETH (Эфириум), BNB (Binance Coin), DOGE (Dogecoin), DOT (Polkadot), " +
                "ADA (Cardano). Я могу:\n" +
                "- сообщить стоимость криптовалюты – нажмите cryptocurrency\n" +
                "- подписать вас на рассылку об изменении стоимости криптовалюты – нажмите subscribe\n" +
                "- отписать вас от рассылки о стоимости криптовалюты – нажмите unsubscribe\n" +
                "- подписать вас на уведомление об изменении стоимости криптовалюты на заданный процент – нажмите luxurySubscribe");
        userDataCache.setUsersCurrentBotState(userId,BotState.MENU);

        replyToUser.setReplyMarkup(mainMenuService.getMainMenuKeyboard());

        return replyToUser;
    }
}
