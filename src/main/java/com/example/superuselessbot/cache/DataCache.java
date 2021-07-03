package com.example.superuselessbot.cache;

import com.example.superuselessbot.botapi.BotState;

public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    void addUserSub(int userId, String figi);

    void deleteUserSub(int userId, String figi);

}