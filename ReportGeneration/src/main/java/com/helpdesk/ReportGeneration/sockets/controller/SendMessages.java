package com.helpdesk.ReportGeneration.sockets.controller;

import com.helpdesk.ReportGeneration.model.User;
import com.helpdesk.ReportGeneration.sockets.service.ChatStoreService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class SendMessages {

	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	SendMessages(SimpMessagingTemplate simpMessagingTemplate){
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@Autowired
	private ChatStoreService chatStoreService;

	public void sendResponse(User messageConverted, String socketId) throws JSONException {
		Map<String, String> responseToSend = messageFormatter();
		System.out.println("sending message back to user");
		this.simpMessagingTemplate.convertAndSend("/socket-publisher", responseToSend);
	}

	public Map<String, String> messageFormatter() throws JSONException {
		Map<String, String> resposeToSend = new HashMap<>();
		resposeToSend.put("emailId", "");
		resposeToSend.put("content", "");
		resposeToSend.put("type", "");
		System.out.println("sender = " + "");
		resposeToSend.put("sender", "");
		return resposeToSend;
	}

	private MessageHeaders createHeaders(String socketId){
		SimpMessageHeaderAccessor simpMessageHeaderAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		simpMessageHeaderAccessor.setSessionId(socketId);
		simpMessageHeaderAccessor.setLeaveMutable(true);
		return simpMessageHeaderAccessor.getMessageHeaders();
	}
}