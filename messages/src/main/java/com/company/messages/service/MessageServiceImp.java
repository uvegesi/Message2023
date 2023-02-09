package com.company.messages.service;

import com.company.messages.controller.UserStatistics;
import com.company.messages.dto.CreateMsgDto;
import com.company.messages.model.Message;
import com.company.messages.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    private static List<Message> messages = new ArrayList<>();
    static {
        messages.add(new Message(1, "Béla", "hellősziaszevasz"));
        messages.add(new Message(2, "Géza","Mi van Béláim?"));
        messages.add(new Message(3, "Feri", "Nem az a Feri..."));
    }

    public void createMessage(CreateMsgDto message) {
        UserStatistics userStatistics = new UserStatistics();
        /*
        if (userStatistics.getUser() == null) {
            userStatistics.setUser(new User(message.getAuthor()));
        } else {
            message.setAuthor(userStatistics.getUser().getName());
        }

         */
        Message m = new Message(message.getMsgNum(), message.getAuthor(), message.getText());
        m.setCreateDate(LocalDateTime.now());
        messages.add(m);
    }

    public void updateMessage(int id, Message message) {
        messages.remove(id);
        messages.add(message);
    }

    public void updateMsgCreationDate() {
        for (Message m : messages) {
            m.setCreateDate(LocalDateTime.now());
        }
    }

    public void deleteMessage(int id) {
        messages.remove(id);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getOneMessage(int id) {
        for (Message m : messages) {
            if (m.getMsgNum() == id) {
                return m.getMsg();
            }
        }
        return null;
    }

    public Message getAFullMessage(int id) {
        for (Message m : messages) {
            if (m.getMsgNum() == id) {
                return m;
            }
        }
        return null;
    }
}
