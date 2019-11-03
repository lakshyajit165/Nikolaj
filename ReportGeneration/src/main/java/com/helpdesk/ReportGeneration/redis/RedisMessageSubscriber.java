package com.helpdesk.ReportGeneration.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.ReportGeneration.model.User;
import com.helpdesk.ReportGeneration.sockets.controller.RecieveMessages;
import com.helpdesk.ReportGeneration.sockets.controller.SendMessages;
import com.helpdesk.ReportGeneration.sockets.model.SocketStore;
import com.helpdesk.ReportGeneration.sockets.redisrepo.ISocketIdRepo;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class RedisMessageSubscriber implements MessageListener {

	ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private RecieveMessages recieveMessages;
	@Autowired
	private SendMessages sendMessages;
	@Autowired
	private ISocketIdRepo iSocketIdRepo;

	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		try {
			System.out.println("subscriber in socket server" + message);
			System.out.println("channel = " + message.getChannel());
			User user = objectMapper.readValue(message.getBody(), User.class);
			Optional<SocketStore> socketStoreObject = iSocketIdRepo.findById(user.getEmailId());
			String socketId = socketStoreObject.get().getSocketId();
			if(user.getSender().contains("com"))
			{
				SocketStore socketStore = socketStoreObject.get();
				socketStore.setCsrEmailId(user.getSender());
				iSocketIdRepo.save(socketStore);
			}
//			sendMessages.sendResponse(user, socketId);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		System.out.println("message.getbody = " + message.getBody());
		System.out.println("Message received: " + new String(message.getBody()));
	}


}