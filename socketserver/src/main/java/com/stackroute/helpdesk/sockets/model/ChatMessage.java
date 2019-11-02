package com.stackroute.helpdesk.sockets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

	private String role;
	private String message;
	private Date date;
	private String user;
	private int hours;
	private int minutes;

}
