package com.helpdesk.ReportGeneration.sockets.redisconfig;


import com.helpdesk.ReportGeneration.model.User;
import com.helpdesk.ReportGeneration.redis.RedisMessageSubscriber;
import com.helpdesk.ReportGeneration.sockets.model.Chats;
import com.helpdesk.ReportGeneration.sockets.model.SocketStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.stackroute.helpdesk.sockets")
@EnableRedisRepositories("com.stackroute.helpdesk.sockets.redisrepo")
public class RedisConfigurations {


	@Autowired
	@Lazy
    RedisMessageSubscriber redisMessageSubscriber;

	@Value("${REDIS_HOST}")
	private String redisHostName;
	@Value("${REDIS_PORT}")
	private String redisPortNum;

	@Bean("jedis")
	public JedisConnectionFactory jedisConnectionFactory(){
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
	public RedisTemplate<String, SocketStore> socketStoreRedisTemplate() {
		RedisTemplate<String, SocketStore> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		System.out.println("socketStore Redis Template created");
		return redisTemplate;
	}

	@Bean
	@DependsOn("jedis")
	@Lazy
	public RedisTemplate<String, Chats> chatStoreRedisTemplate() {
		RedisTemplate<String, Chats> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		System.out.println("socketStore Redis Template created");
		return redisTemplate;
	}

	@Bean
	@DependsOn("jedis")
	@Lazy
	public MessageListenerAdapter messageListenerForRedis() {
		return new MessageListenerAdapter(redisMessageSubscriber);
	}

	@Bean
	@Lazy
	RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container
				= new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(messageListenerForRedis(), getListOfPatternTopics());
		return container;
	}

	@Bean
	@Qualifier("userBean")
	public RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
 	}

	@Bean
	@Lazy
	public List<PatternTopic> getListOfPatternTopics(){
		List<PatternTopic> patternTopicList = new ArrayList<>();
		patternTopicList.add(new PatternTopic("reports"));
		return patternTopicList;
	}

}
