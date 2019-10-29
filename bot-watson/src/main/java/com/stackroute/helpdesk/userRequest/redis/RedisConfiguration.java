package com.stackroute.helpdesk.userRequest.redis;

import com.stackroute.helpdesk.userRequest.controller.ChatController;
import com.stackroute.helpdesk.userRequest.model.ChatMessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Value;

>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e

@Configuration
public class RedisConfiguration {

<<<<<<< HEAD
=======
	@Value("${REDIS_HOST}")
	private String redisHostName;
	@Value("${REDIS_PORT}")
	private String redisPortNum;

>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
	@Bean
	@Qualifier("jedis")
	JedisConnectionFactory jedisConFactory() {
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
<<<<<<< HEAD
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
=======
		jedisConFactory.setHostName(redisHostName);
		jedisConFactory.setPort(Integer.parseInt(redisPortNum));
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
		return jedisConFactory;
	}

	@Bean
	@Qualifier("messagingTemplate")
	public RedisTemplate<String, ChatMessageFormat> getMessageRedisTemplate() {
		RedisTemplate<String, ChatMessageFormat> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}

}
