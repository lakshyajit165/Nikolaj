package com.stackroute.helpdesk.csrservice.livechat.redis.redisconfig;

import com.stackroute.helpdesk.csrservice.livechat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.time.Duration;

@Configuration
public class RedisConfiguration {

	@Value("${REDIS_HOST}")
	private String redisHostName;
	@Value("${REDIS_PORT}")
	private String redisPortNum;

	@Bean("jedis")
	public JedisConnectionFactory jedisConFactory() {
		System.out.println("atleast!!! jedis connection started establishing");
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisHostName);
		redisStandaloneConfiguration.setPort(Integer.parseInt(redisPortNum));
		redisStandaloneConfiguration.setPassword(RedisPassword.of("nikolaj"));

		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout

		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
				jedisClientConfiguration.build());
		System.out.println("congratulations!!! jedis connection established");
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
