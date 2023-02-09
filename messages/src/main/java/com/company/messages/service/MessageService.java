package com.company.messages.service;

import com.company.messages.dto.CreateMsgDto;
import com.company.messages.model.Message;

import java.util.List;

public interface MessageService {

    public abstract void createMessage(CreateMsgDto message);
    public abstract void updateMessage(int id, Message message);
    public abstract void deleteMessage(int id);
    public abstract List<Message> getMessages();
    public abstract String getOneMessage(int id);
}
