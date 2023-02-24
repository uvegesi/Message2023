package com.company.messages.controller;

import com.company.messages.model.Message;
import com.company.messages.model.UserStatistics;
import com.company.messages.service.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Scope(
        scopeName = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)

public class MessageServiceController {

    private MessageServiceImp messageService;
    private UserStatistics userStatistics;

    @Autowired
    public MessageServiceController(MessageServiceImp messageService, UserStatistics userStatistics) {
        this.messageService = messageService;
        this.userStatistics = userStatistics;
    }

    @RequestMapping(value="/messages", method= RequestMethod.GET)
    public String showUserData(Model model) {
        List<Message> messageList = messageService.getMessages();
        for (Message m : messageList) {
            if (m.getCreateDate() == null) {
                m.setCreateDate(LocalDateTime.now());
            }
        }
        model.addAttribute("messages", messageList);
        return "messages";
    }

}
