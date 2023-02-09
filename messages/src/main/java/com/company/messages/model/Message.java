package com.company.messages.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class Message {

    private int msgNum;

    @NotNull(message = "Author cannot be empty!")
    @Size(min = 2, max = 15, message = "Not between {1} and {2} characters!")
    private String author;

    @NotNull(message = "Message cannot be empty!")
    @Size(min = 5, max = 50, message = "Not between {1} and {2} characters!")
    private String msg;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime createDate;

    public Message(int msgNum, String author, String msg, LocalDateTime createDate) {
        this.msgNum = msgNum;
        this.author = author;
        this.msg = msg;
        this.createDate = createDate;
    }

    public Message(int msgNum, String author, String msg) {
        this.msgNum = msgNum;
        this.author = author;
        this.msg = msg;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
