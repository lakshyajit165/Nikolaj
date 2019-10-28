package com.stackroute.helpdesk.sockets.redisconfig;

import com.stackroute.helpdesk.config.JedisConfig;
import com.stackroute.helpdesk.redis.RedisConfiguration;
import com.stackroute.helpdesk.sockets.model.SocketStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan("com.stackroute.helpdesk.sockets")
@EnableRedisRepositories("com.stackroute.helpdesk.sockets.redisrepo")
public class RedisConfigurations {

	@Autowired
	private JedisConfig jedisConfig;

	@Bean
	public RedisTemplate<String, Object> socketStoreRedisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConfig.jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		System.out.println("socketStore Redis Template created");
		return redisTemplate;
	}
}
