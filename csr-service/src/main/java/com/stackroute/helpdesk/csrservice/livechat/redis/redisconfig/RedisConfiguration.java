package com.stackroute.helpdesk.csrservice.livechat.redis.redisconfig;

import com.stackroute.helpdesk.csrservice.livechat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfiguration {
	@Bean
	@Qualifier("jedis")
	JedisConnectionFactory jedisConFactory() {
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}

	@Bean
	@Qualifier("csr_published_messages")
	public RedisTemplate<String, ChatMessage> getMessageRedisTemplate() {
		RedisTemplate<String, ChatMessage> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	};
}
