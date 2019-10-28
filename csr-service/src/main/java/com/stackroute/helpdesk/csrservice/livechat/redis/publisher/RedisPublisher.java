package com.stackroute.helpdesk.csrservice.livechat.redis.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.csrservice.livechat.model.ChatMessage;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {

	@Autowired
	@Qualifier("csr_published_messages")
	private RedisTemplate redisTemplate;

	public void publish(ChatMessage chatMessage) throws JsonProcessingException, JSONException {
		ObjectMapper objectMapper = new ObjectMapper();
		String messagePublished = objectMapper.writeValueAsString(chatMessage);
		redisTemplate.convertAndSend(chatMessage.getSender()+"_csr_messages", messagePublished);
		System.out.println("csr publishing his messages");
	}
}
