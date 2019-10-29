package com.stackroute.helpdesk.sockets.controller;

import com.stackroute.helpdesk.model.User;
import com.stackroute.helpdesk.sockets.service.ChatStoreService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

	@Autowired
	private ChatStoreService chatStoreService;

	public void sendResponse(User messageConverted, String socketId) throws JSONException {
		Map<String, String> responseToSend = messageFormatter(messageConverted);
		System.out.println("sending message back to user");
		this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+socketId, responseToSend);
	}

	public Map<String, String> messageFormatter(User message) throws JSONException {
		Map<String, String> resposeToSend = new HashMap<>();
		chatStoreService.updateChatHistory(message.getContent(), message.getSender(), message.getType(), message.getEmailId());
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