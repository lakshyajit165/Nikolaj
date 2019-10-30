package com.stackroute.helpdesk.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.model.User;
import com.stackroute.helpdesk.sockets.controller.RecieveMessages;
import com.stackroute.helpdesk.sockets.controller.SendMessages;
import com.stackroute.helpdesk.sockets.model.SocketStore;
import com.stackroute.helpdesk.sockets.redisrepo.ISocketIdRepo;
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
			sendMessages.sendResponse(user, socketId);
		} catch (IOException | JSONException ioException) {
			ioException.printStackTrace();
		}
		System.out.println("message.getbody = " + message.getBody());
		System.out.println("Message received: " + new String(message.getBody()));
	}

//	@Override
//	public void onMessage(String channel, String message) {
//		System.out.println("  <<< SUBSCRIBE< Channel:" + channel + " >Message received:" + message );
//		//When a quit message is received, the subscription is canceled (passively)
//		if(message.equalsIgnoreCase("quit")){
////			this.unsubscribe(channel);
//		}
//	}
}