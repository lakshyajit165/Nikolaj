//package com.stackroute.helpdesk.csrservice.livechat.socket.redisconfig;
//
//import com.stackroute.helpdesk.config.JedisConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.GenericToStringSerializer;
//
//@Configuration
//@ComponentScan("com.stackroute.helpdesk.sockets")
//@EnableRedisRepositories("com.stackroute.helpdesk.sockets.redisrepo")
//public class RedisConfigurations {
//
//	@Autowired
//	private JedisConfig jedisConfig;
//
//	@Bean
//	public RedisTemplate<String, Object> socketStoreRedisTemplate() {
//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(jedisConfig.jedisConnectionFactory());
//		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//		System.out.println("socketStore Redis Template created");
//		return redisTemplate;
//	}
//}
