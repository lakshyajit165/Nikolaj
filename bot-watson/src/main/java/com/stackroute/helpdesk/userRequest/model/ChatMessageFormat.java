package com.stackroute.helpdesk.userRequest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageFormat implements Serializable {

    @Id
    private String id;
    private String message;
    private String emailId;

//    public Message(String id, String name, Long emailId) {
//        this.id = id;
//        this.message = name;
//        this.emailId = emailId;
//    }
//    public Message(){}
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return message;
//    }
//
//    public void setName(String name) {
//        this.message = name;
//    }
//
//    public Long getemailId() {
//        return emailId;
//    }
//
//    public void setemailId(Long emailId) {
//        this.emailId = emailId;
//    }
}
