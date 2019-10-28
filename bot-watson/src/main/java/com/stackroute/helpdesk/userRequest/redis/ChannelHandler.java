package com.stackroute.helpdesk.userRequest.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.userRequest.controller.ChatController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

@Service
public class ChannelHandler {

	@Autowired
	private RedisConfiguration redisConfiguration;

	@Autowired
	private ChatController chatController;

	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container
				= new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConfiguration.jedisConFactory());
		container.addMessageListener(messageListener(), getPatternTopic());
		return container;
	}
	@Bean
	public ObjectMapper getObjectMapper(){
		return new ObjectMapper();
	}

	public PatternTopic getPatternTopic(){
		return new PatternTopic("bot_message");
	}

	@Bean
	public MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(chatController);
	}

}
