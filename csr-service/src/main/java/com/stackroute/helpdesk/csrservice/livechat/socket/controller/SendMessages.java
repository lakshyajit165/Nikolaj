package com.stackroute.helpdesk.csrservice.livechat.socket.controller;

import com.stackroute.helpdesk.csrservice.livechat.model.ChatMessage;
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

	public void sendResponse(ChatMessage messageConverted, String socketId) throws JSONException {
		Map<String, String> responseToSend = messageFormatter(messageConverted);
		this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+socketId, responseToSend);
	}

	public Map<String, String> messageFormatter(ChatMessage message) throws JSONException {
		ChatMessage user = new ChatMessage();
		Map<String, String> resposeToSend = new HashMap<>();
		resposeToSend.put("emailId", message.getEmailId());
		resposeToSend.put("content", message.getContent());
		resposeToSend.put("type", message.getType());
		resposeToSend.put("sender", message.getSender());
		resposeToSend.put("hours", String.valueOf(message.getHours()));
		resposeToSend.put("minutes", String.valueOf(message.getMinutes()));
		return resposeToSend;
	}

	private MessageHeaders createHeaders(String socketId){
		SimpMessageHeaderAccessor simpMessageHeaderAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		simpMessageHeaderAccessor.setSessionId(socketId);
		simpMessageHeaderAccessor.setLeaveMutable(true);
		return simpMessageHeaderAccessor.getMessageHeaders();
	}
}