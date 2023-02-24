package com.company.messages.controller;

import com.company.messages.dto.CreateMsgDto;
import com.company.messages.model.Message;
import com.company.messages.model.User;
import com.company.messages.model.UserStatistics;
import com.company.messages.service.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MessageServiceController2 {

    private final MessageServiceImp messageService;
    private final UserStatistics userStatistics;

    @Autowired
    public MessageServiceController2(MessageServiceImp messageService, UserStatistics userStatistics, CreateMsgDto createMsgDto) {
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

    @GetMapping(value = "/create")
    public String showCreateAMessage(Model model) {
        CreateMsgDto createMsgDto = new CreateMsgDto();
        User user = userStatistics.getUser();
        if (user != null && user.getName() != null) {
            createMsgDto.setAuthor(user.getName());
        }
        model.addAttribute("message", createMsgDto);
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
        userStatistics.setMessageCounter(userStatistics.getMessageCounter() + 1);
        Map<User, Integer> counter = userStatistics.getMsgCounter();
        Integer count = counter.get(u);
        if (count == null) {
            count = 0;
        }
        counter.put(u, count + 1);
        userStatistics.setMsgCounter(counter);
        System.out.println(userStatistics.getMsgCounter().get(u));
        return "redirect:/messages";
    }

    @GetMapping(path = "/showuserstatistics")
    public String listMessages(Model model) {
        model.addAttribute("numofmsgsinasess", userStatistics.getMessageCounter());
        int n = userStatistics.getMsgCounter().keySet().size();
        int counter = 0;
        for (Message msg: messageService.getMessages()) {
            for (User user : userStatistics.getMsgCounter().keySet())
                if (msg.getAuthor().equals(user.getName())) {
                    counter++;
                }
        }
        model.addAttribute("numofusersinasess", n);
        model.addAttribute("counter", counter);
        return "userstatistics";
    }
}
