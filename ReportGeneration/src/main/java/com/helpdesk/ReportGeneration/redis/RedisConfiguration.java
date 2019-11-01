package com.helpdesk.ReportGeneration.redis;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {

//	@Autowired
//	RedisMessageSubscriber redisMessageSubscriber;
//	@Autowired
//	JedisConfig jedisConfig;
//
//	@Bean
//	MessageListenerAdapter messageListenerForRedis( ) {
//		return new MessageListenerAdapter(redisMessageSubscriber);
//	}
//
//	@Bean
//	@Qualifier("redisTemp")
//	public RedisTemplate<String, User> redisTemplate() {
//		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(jedisConfig.jedisConnectionFactory());
//		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//		return redisTemplate;
// 	}
}
