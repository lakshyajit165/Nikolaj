package com.stackroute.helpdesk.redis;

import com.stackroute.helpdesk.config.JedisConfig;
import com.stackroute.helpdesk.model.User;
import com.stackroute.helpdesk.sockets.model.SocketStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfiguration {


	@Autowired
	RedisMessageSubscriber redisMessageSubscriber;
	@Autowired
	JedisConfig jedisConfig;

	@Bean
	MessageListenerAdapter messageListener( ) {
		return new MessageListenerAdapter(redisMessageSubscriber);
	}

	@Bean
	@Qualifier("redisTemp")
	public RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		System.out.println("jedis config = " + jedisConfig.jedisConnectionFactory());
		redisTemplate.setConnectionFactory(jedisConfig.jedisConnectionFactory());
		System.out.println("after jedis");
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		System.out.println("redis");
		return redisTemplate;
 	}
}
