package com.helpdesk.ReportGeneration.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class User implements Serializable {

    @Id
    private String emailId;
    private String type;
    private String content;
    private String sender;


    public User() {
    }

    public User(String emailId, String type, String content, String sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.emailId = emailId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}
