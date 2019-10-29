package com.stackroute.helpdesk.csrservice.livechat.redis.redisconfig;

import com.stackroute.helpdesk.csrservice.livechat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfiguration {

	@Value("${REDIS_HOST}")
	private String redisHostName;
	@Value("${REDIS_PORT}")
	private String redisPortNum;

	@Bean("jedis")
	public JedisConnectionFactory jedisConFactory() {
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
		jedisConFactory.setPassword("nikolaj");
		jedisConFactory.setHostName(redisHostName);
		jedisConFactory.setPort(Integer.parseInt(redisPortNum));
		return jedisConFactory;
	}

	@Bean
	@DependsOn("jedis")
	@Lazy
	public RedisTemplate<String, ChatMessage> getMessageRedisTemplate() {
		RedisTemplate<String, ChatMessage> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}
}
