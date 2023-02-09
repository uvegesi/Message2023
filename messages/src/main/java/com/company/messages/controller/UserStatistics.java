package com.company.messages.controller;

import com.company.messages.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)

public class UserStatistics {

    private User user;
    private int messageCounter;
    private Map<User, Integer> msgCounter;

    public UserStatistics() {
        this.msgCounter = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMessageCounter() {
        return messageCounter;
    }

    public void setMessageCounter(int messageCounter) {
        this.messageCounter = messageCounter;
    }

    public Map<User, Integer> getMsgCounter() {
        return msgCounter;
    }

    public void setMsgCounter(Map<User, Integer> msgCounter) {
        this.msgCounter = msgCounter;
    }
}
