package com.stackroute.helpdesk.sockets.redisconfig;

import com.stackroute.helpdesk.config.JedisConfig;
import com.stackroute.helpdesk.model.User;
import com.stackroute.helpdesk.redis.RedisMessageSubscriber;
import com.stackroute.helpdesk.sockets.model.Chats;
import com.stackroute.helpdesk.sockets.model.SocketStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.stackroute.helpdesk.sockets")
@EnableRedisRepositories("com.stackroute.helpdesk.sockets.redisrepo")
public class RedisConfigurations {

//	@Autowired
//	private JedisConfig jedisConfig;

	@Autowired
	RedisMessageSubscriber redisMessageSubscriber;

	@Value("${REDIS_HOST}")
	private String redisHostName;
	@Value("${REDIS_PORT}")
	private String redisPortNum;

	@Bean("jedis")
	public JedisConnectionFactory jedisConnectionFactory(){
		System.out.println("atleast!!! jedis connection started establishing");
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
		jedisConFactory.setPassword("nikolaj");
		jedisConFactory.setHostName(redisHostName);
		jedisConFactory.setPort(Integer.parseInt(redisPortNum));
		System.out.println("congratulations!!! jedis connection established");
		return jedisConFactory;
	}

	@Bean
	@DependsOn("jedis")
	public RedisTemplate<String, SocketStore> socketStoreRedisTemplate() {
		RedisTemplate<String, SocketStore> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		System.out.println("socketStore Redis Template created");
		return redisTemplate;
	}

	@Bean
	@DependsOn("jedis")
	public RedisTemplate<String, Chats> chatStoreRedisTemplate() {
		RedisTemplate<String, Chats> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		System.out.println("socketStore Redis Template created");
		return redisTemplate;
	}

	@Bean
	@DependsOn("jedis")
	public MessageListenerAdapter messageListenerForRedis() {
		return new MessageListenerAdapter(redisMessageSubscriber);
	}

	@Bean
	@DependsOn("jedis")
	public RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}

	@Bean
	RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container
				= new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(messageListenerForRedis(), getListOfPatternTopics());
		return container;
	}

	@Bean
	public List<PatternTopic> getListOfPatternTopics(){
		List<PatternTopic> patternTopicList = new ArrayList<>();
		patternTopicList.add(new PatternTopic("*_chat_messages"));
		patternTopicList.add(new PatternTopic("*_csr_messages"));
		return patternTopicList;
	}

}
