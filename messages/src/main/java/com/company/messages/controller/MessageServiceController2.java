package com.company.messages.controller;

import com.company.messages.dto.CreateMsgDto;
import com.company.messages.model.Message;
import com.company.messages.model.User;
import com.company.messages.service.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MessageServiceController2 {

    private final MessageServiceImp messageService;
    private UserStatistics userStatistics;

    @Autowired
    public MessageServiceController2(MessageServiceImp messageService, UserStatistics userStatistics) {
        this.messageService = messageService;
        this.userStatistics = userStatistics;
    }

    @RequestMapping(value = "/showonemessage", method = RequestMethod.GET)
    public String showUserData(@RequestParam("user_id") int msgNum, Model model) {
        String message = messageService.getOneMessage(msgNum);
        model.addAttribute("message", message);
        return "onemessage";
    }

    @RequestMapping(value = {"/anumofmessages/{numOfMsgs}", "/anumofmessages"}, method = RequestMethod.GET)
    public String showANumOfMessages(@PathVariable(value = "numOfMsgs", required = false)
                                             Integer numOfMsgs, Model model) {
        if (numOfMsgs == null) {
            numOfMsgs = 3;
        }
        List<Message> displayedMessages = new ArrayList<>();
        for (int i = 0; i < numOfMsgs; i++) {
            Message message = messageService.getAFullMessage(i);
            displayedMessages.add(message);
        }
        model.addAttribute("anumofmsgs", displayedMessages);
        return "anumofmsgs";
    }

    @RequestMapping(value = "/onemessage/{msgNum}", method = RequestMethod.GET)
    public String showMsgWithId(@PathVariable("msgNum") int msgNum, Model model) {
        model.addAttribute("onemessage", messageService.getOneMessage(msgNum));
        return "amessage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateAMessage(Model model) {
        CreateMsgDto createMsgDto = new CreateMsgDto();
        User user = new User();
        userStatistics.setUser(user);
        if (userStatistics.getUser().getName() == null) {
            model.addAttribute("message", createMsgDto);
        } else {
            createMsgDto.setAuthor(userStatistics.getUser().getName());
            model.addAttribute("message", createMsgDto);
        }
        //model.addAttribute("author", createMsgDto.getAuthor());

        return "create";
    }

    @PostMapping(path = "/create")
    public String createAMessage(@Valid @ModelAttribute("message") CreateMsgDto msg, BindingResult br) {
        if (br.hasErrors()) {
            return "create";
        }

        messageService.createMessage(msg);
        User u = new User(msg.getAuthor());
        userStatistics.setUser(u);
        return "redirect:/messages";
    }

    public String listMessages(Model model) {
        Map<User, Integer> map = userStatistics.getMsgCounter();
        return "statistics";
    }
}
