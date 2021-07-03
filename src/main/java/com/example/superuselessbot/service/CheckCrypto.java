package com.example.superuselessbot.service;

import com.example.superuselessbot.utils.UtilClass;

public class CheckCrypto {
    public static boolean checkCrypto(String figi){
        return UtilClass.getCrypts().contains(figi);
    }
}
