package com.stackroute.helpdesk.csrservice.livechat.redis.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.csrservice.livechat.model.ChatMessage;
import com.stackroute.helpdesk.csrservice.livechat.model.SocketStore;
import com.stackroute.helpdesk.csrservice.livechat.socket.controller.SendMessages;
import com.stackroute.helpdesk.csrservice.livechat.socket.redisrepo.ISocketIdRepo;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class RedisSubscriber implements MessageListener {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private ISocketIdRepo iSocketIdRepo;

	@Autowired
	private SendMessages sendMessages;

	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		try {
			ChatMessage chatMessage = objectMapper.readValue(message.getBody(), ChatMessage.class);
			Optional<SocketStore> socketStoreObject = iSocketIdRepo.findById(chatMessage.getSender());
			String socketId = socketStoreObject.get().getSocketId();
			sendMessages.sendResponse(chatMessage, socketId);
		} catch (IOException | JSONException ioException) {
			ioException.printStackTrace();
		}
		System.out.println("Message received: " + new String(message.getBody()));
	}
}
