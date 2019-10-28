package com.stackroute.helpdesk.sockets.controller;

import com.stackroute.helpdesk.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageHeaderInitializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SendMessages {

	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	SendMessages(SimpMessagingTemplate simpMessagingTemplate){
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	public void sendResponse(User messageConverted, String socketId) throws JSONException {
		Map<String, String> responseToSend = messageFormatter(messageConverted);
		this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+socketId, responseToSend);
	}

	public Map<String, String> messageFormatter(User message) throws JSONException {
		User user = new User();
		Map<String, String> resposeToSend = new HashMap<>();
		resposeToSend.put("emailId", message.getEmailId());
		resposeToSend.put("content", message.getContent());
		resposeToSend.put("type", message.getType());
		System.out.println("sender = " + message.getSender());
		resposeToSend.put("sender", message.getSender());
		return resposeToSend;
	}

	private MessageHeaders createHeaders(String socketId){
		SimpMessageHeaderAccessor simpMessageHeaderAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		simpMessageHeaderAccessor.setSessionId(socketId);
		simpMessageHeaderAccessor.setLeaveMutable(true);
		return simpMessageHeaderAccessor.getMessageHeaders();
	}
}