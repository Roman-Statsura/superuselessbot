package com.example.superuselessbot.config;

import com.example.superuselessbot.MyBot;
import com.example.superuselessbot.botapi.TelegramFacade;
import com.example.superuselessbot.cache.UserDataCache;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String token;
    private String userName;
    private String webHookPath;

    @Bean
    public MyBot myBot(TelegramFacade telegramFacade, UserDataCache userDataCache){
        MyBot myBot = new MyBot(telegramFacade,userDataCache);
        myBot.setToken(token);
        myBot.setUserName(userName);
        myBot.setWebHookPath(webHookPath);

        return myBot;
    }
}