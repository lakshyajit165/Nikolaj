package com.stackroute.helpdesk.csrservice.livechat.redis.redisconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.csrservice.livechat.redis.subscriber.RedisSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

@Service
public class ChannelHandler {

	@Autowired
	private RedisConfiguration redisConfiguration;

	@Autowired
	private RedisSubscriber redisSubscriber;

	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container
				= new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConfiguration.jedisConFactory());
		container.addMessageListener(messageListener(), getChannelTopic());
		return container;
	}
	@Bean
	public ObjectMapper getObjectMapper(){
		return new ObjectMapper();
	}

	public ChannelTopic getChannelTopic(){
		return new ChannelTopic("csr_message");
	}

	@Bean
	public MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(redisSubscriber);
	}

}

