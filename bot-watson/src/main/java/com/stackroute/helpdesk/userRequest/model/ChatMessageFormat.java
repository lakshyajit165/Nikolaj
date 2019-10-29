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

}
