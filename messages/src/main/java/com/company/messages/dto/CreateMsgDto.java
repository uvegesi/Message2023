package com.company.messages.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@SessionScope
public class CreateMsgDto {

    @NotNull(message = "Message cannot be empty!")
    @Size(min = 5, max = 50, message = "Not between {1} and {2} characters!")
    private String text;

    @NotNull(message = "Author cannot be empty!")
    @Size(min = 2, max = 15, message = "Not between {1} and {2} characters!")
    private String author;

    private int msgNum;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }
}
