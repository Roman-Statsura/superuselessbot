package com.example.superuselessbot.cache;

import com.example.superuselessbot.botapi.BotState;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDataCache implements DataCache{
    private final Map<Integer, BotState> usersBotStates = new HashMap<>();
    private final Map<Integer, Set<String>> subscriptions = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(int userId) {
        BotState botState = usersBotStates.get(userId);
        if (botState == null) {
            botState = BotState.NEW;
        }

        return botState;
    }

    @Override
    public void addUserSub(int userId, String figi) {
        if (!subscriptions.containsKey(userId)) {
            subscriptions.put(userId, new HashSet<>());
        }
        subscriptions.get(userId).add(figi);
    }

    @Override
    public void deleteUserSub(int userId, String figi) {
        subscriptions.get(userId).remove(figi);
    }
    public Set<String> getSubByUser(Integer userId){
        return subscriptions.get(userId);
    }

    public Map<Integer, Set<String>> getSubscriptions() {
        return subscriptions;
    }

    public void deleteAllSubs(Integer userId){
        subscriptions.get(userId).clear();
    }
    public boolean isUserANew(Integer userId){
        return !subscriptions.containsKey(userId);
    }
}
