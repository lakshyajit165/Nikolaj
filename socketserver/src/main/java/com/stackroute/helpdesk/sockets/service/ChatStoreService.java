package com.stackroute.helpdesk.sockets.service;

import com.stackroute.helpdesk.sockets.model.ChatMessage;
import com.stackroute.helpdesk.sockets.model.Chats;
import com.stackroute.helpdesk.sockets.redisrepo.IChatStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class ChatStoreService {

//	@Autowired
	private IChatStore iChatStore;

	@Autowired
	public ChatStoreService(IChatStore iChatStore){
		this.iChatStore = iChatStore;
	}

	public Chats getChatHistory(String emailId) {
		Optional<Chats> optionalChatsList = iChatStore.findById(emailId);
		if(optionalChatsList.isPresent()){
			Chats chatsList = optionalChatsList.get();
			return chatsList;
		}
		else {
			String currentHour = String.valueOf(LocalDateTime.now().getHour());
			String currentMinute = String.valueOf(LocalDateTime.now().getMinute());
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setMessage("Hi I am Optimus, how may I help you ");
			chatMessage.setUser("bot");
			chatMessage.setDate(new Date());
			chatMessage.setHour(currentHour);
			chatMessage.setMinute(currentMinute);
			List<ChatMessage> chatsList = new ArrayList<>();
			chatsList.add(chatMessage);
			updateChatHistory("Hi I am Optimus, how may I help you ", "bot", "", emailId, currentHour, currentMinute);
			return new Chats(emailId, chatsList);
		}
	}

	public void updateChatHistory(String message, String user, String role, String emailId, String hours, String minutes){
		Optional<Chats> chatsList = iChatStore.findById(emailId);
		List<ChatMessage> chatMessageList = null;
		if(chatsList.isPresent()){
			chatMessageList = chatsList.get().getChatMessages();
		}
		else {
			chatMessageList = new ArrayList<>();
		}
		ChatMessage chatMessage = addChatMessage(message, user, role, emailId, hours, minutes);
		chatMessageList.add(chatMessage);
		System.out.println("updating chat messages adding 1 more as = " + chatMessage.getMessage());
		iChatStore.save(new Chats(emailId, chatMessageList));
	}

	public ChatMessage addChatMessage(String message, String user, String role, String emailId, String hours, String minutes){
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setDate(new Date());
		chatMessage.setMessage(message);
		chatMessage.setRole(role);
		chatMessage.setUser(user);
		chatMessage.setHour(hours);
		chatMessage.setMinute(minutes);
		return chatMessage;
	}

}
