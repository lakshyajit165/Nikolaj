package com.stackroute.helpdesk.userRequest.redis;

import com.stackroute.helpdesk.userRequest.controller.ChatController;
import com.stackroute.helpdesk.userRequest.model.ChatMessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RedisConfiguration {

	@Value("${REDIS_HOST}")
	private String redisHostName;
	@Value("${REDIS_PORT}")
	private String redisPortNum;

	@Bean("jedis")
	JedisConnectionFactory jedisConFactory() {
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
		jedisConFactory.setHostName(redisHostName);
		jedisConFactory.setPort(Integer.parseInt(redisPortNum));
		jedisConFactory.setPassword("nikolaj");
		return jedisConFactory;
	}

	@Bean
	@DependsOn("jedis")
	@Lazy
	@Qualifier("messagingTemplate")
	public RedisTemplate<String, ChatMessageFormat> getMessageRedisTemplate() {
		RedisTemplate<String, ChatMessageFormat> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}

}
