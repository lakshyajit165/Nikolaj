package com.stackroute.helpdesk.csrservice.livechat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
	private String type;
	private String content;
	private String sender;
	private String emailId;
	private String hours;
	private String minutes;
}
