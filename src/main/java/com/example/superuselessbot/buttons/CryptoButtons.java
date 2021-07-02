package com.example.superuselessbot.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CryptoButtons {
    public InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonBtc = new InlineKeyboardButton().setText("BTC");
        InlineKeyboardButton buttonEth = new InlineKeyboardButton().setText("ETH");
        InlineKeyboardButton buttonBnb = new InlineKeyboardButton().setText("BNB");
        InlineKeyboardButton buttonDoge = new InlineKeyboardButton().setText("DOGE");
        InlineKeyboardButton buttonDot = new InlineKeyboardButton().setText("DOT");
        InlineKeyboardButton buttonAda = new InlineKeyboardButton().setText("ADA");

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
}
