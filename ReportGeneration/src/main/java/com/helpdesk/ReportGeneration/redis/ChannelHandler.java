package com.helpdesk.ReportGeneration.redis;

import com.helpdesk.ReportGeneration.config.JedisConfig;
import com.helpdesk.ReportGeneration.sockets.redisconfig.RedisConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelHandler {

	@Autowired
	private RedisConfigurations redisConfiguration;
	@Autowired
    JedisConfig jedisConfig;

//	@Bean
//	RedisMessageListenerContainer redisContainer() {
//		RedisMessageListenerContainer container
//				= new RedisMessageListenerContainer();
//		container.setConnectionFactory(jedisConfig.jedisConnectionFactory());
//		container.addMessageListener(redisConfiguration.messageListenerForRedis(), getListOfPatternTopics());
//		return container;
//	}
//
//	@Bean
//	public List<PatternTopic> getListOfPatternTopics(){
//		List<PatternTopic> patternTopicList = new ArrayList<>();
//		patternTopicList.add(new PatternTopic("*_chat_messages"));
//		patternTopicList.add(new PatternTopic("*_csr_messages"));
//		return patternTopicList;
//	}
}
