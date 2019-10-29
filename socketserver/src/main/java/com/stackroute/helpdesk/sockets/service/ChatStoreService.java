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
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setMessage("Hi I am optimus, how may I help you ?");
			chatMessage.setUser("bot");
			chatMessage.setDate(new Date());
			List<ChatMessage> chatsList = new ArrayList<>();
			chatsList.add(chatMessage);
			updateChatHistory("Hi I am optimus, how may I help you ?", "bot", "", emailId);
			return new Chats(emailId, chatsList);
		}
	}

	public void updateChatHistory(String message, String user, String role, String emailId){
		Optional<Chats> chatsList = iChatStore.findById(emailId);
		List<ChatMessage> chatMessageList = null;
		if(chatsList.isPresent()){
			chatMessageList = chatsList.get().getChatMessages();
		}
		else {
			chatMessageList = new ArrayList<>();
		}
		ChatMessage chatMessage = addChatMessage(message, user, role, emailId);
		chatMessageList.add(chatMessage);
		System.out.println("updating chat messages adding 1 more as = " + chatMessage.getMessage());
		iChatStore.save(new Chats(emailId, chatMessageList));
	}

	public ChatMessage addChatMessage(String message, String user, String role, String emailId){
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setDate(new Date());
		chatMessage.setMessage(message);
		chatMessage.setRole(role);
		chatMessage.setUser(user);
		return chatMessage;
	}

}
