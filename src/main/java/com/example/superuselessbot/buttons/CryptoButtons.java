package com.example.superuselessbot.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CryptoButtons {
    public InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonBtc = new InlineKeyboardButton().setText("BTC(биткойн)");
        InlineKeyboardButton buttonEth = new InlineKeyboardButton().setText("ETH(эфириум)");
        InlineKeyboardButton buttonBnb = new InlineKeyboardButton().setText("BNB(binance coin)");
        InlineKeyboardButton buttonDoge = new InlineKeyboardButton().setText("DOGE(dogecoin)");
        InlineKeyboardButton buttonDot = new InlineKeyboardButton().setText("DOT(polkadot)");
        InlineKeyboardButton buttonAda = new InlineKeyboardButton().setText("ADA(cardano)");

        buttonBtc.setCallbackData("BTC");
        buttonEth.setCallbackData("ETH");
        buttonBnb.setCallbackData("BNB");
        buttonDoge.setCallbackData("DOGE");
        buttonDot.setCallbackData("DOT");
        buttonAda.setCallbackData("ADA");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonBtc);
        keyboardButtonsRow1.add(buttonEth);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonBnb);
        keyboardButtonsRow2.add(buttonDoge);

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonDot);
        keyboardButtonsRow3.add(buttonAda);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getUnSubButton(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton button = new InlineKeyboardButton().setText("Отписаться от всех рассылок");
        button.setCallbackData("Unsub");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtonsOptional(Set<String> subs) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonBtc = new InlineKeyboardButton().setText("отписаться от BTC");
        InlineKeyboardButton buttonEth = new InlineKeyboardButton().setText("отписаться от ETH");
        InlineKeyboardButton buttonBnb = new InlineKeyboardButton().setText("отписаться от BNB");
        InlineKeyboardButton buttonDoge = new InlineKeyboardButton().setText("отписаться от DOGE");
        InlineKeyboardButton buttonDot = new InlineKeyboardButton().setText("отписаться от DOT");
        InlineKeyboardButton buttonAda = new InlineKeyboardButton().setText("отписаться от ADA");

        InlineKeyboardButton button = new InlineKeyboardButton().setText("Отписаться от всех рассылок");
        button.setCallbackData("Unsub");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow1.add(button);

        if (subs.contains("BTC")){
            buttonBtc.setCallbackData("BTC");
            keyboardButtonsRow1.add(buttonBtc);
        }
        if (subs.contains("ETH")){
            buttonEth.setCallbackData("ETH");
            keyboardButtonsRow1.add(buttonEth);
        }
        if (subs.contains("BNB")){
            buttonBnb.setCallbackData("BNB");
            keyboardButtonsRow2.add(buttonBnb);
        }
        if (subs.contains("DOGE")){
            buttonDoge.setCallbackData("DOGE");
            keyboardButtonsRow2.add(buttonDoge);
        }
        if (subs.contains("DOT")){
            buttonDot.setCallbackData("DOT");
            keyboardButtonsRow3.add(buttonDot);
        }
        if (subs.contains("ADA")){
            buttonAda.setCallbackData("ADA");
            keyboardButtonsRow3.add(buttonAda);
        }

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
