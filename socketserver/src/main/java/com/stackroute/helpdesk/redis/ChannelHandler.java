package com.stackroute.helpdesk.redis;

import com.stackroute.helpdesk.config.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelHandler {

	@Autowired
	private RedisConfiguration redisConfiguration;
	@Autowired
	JedisConfig jedisConfig;

	@Bean
	RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container
				= new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConfig.jedisConnectionFactory());
		container.addMessageListener(redisConfiguration.messageListenerForRedis(), getListOfPatternTopics());
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
