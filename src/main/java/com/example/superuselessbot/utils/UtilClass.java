package com.example.superuselessbot.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilClass {
    private static List<String> crypts = new ArrayList<>(Arrays.asList("BTC","DOGE","ETH","BNB","DOT","ADA"));

    private UtilClass(){}

    public static List<String> getCrypts(){
        return crypts;
    }
}
