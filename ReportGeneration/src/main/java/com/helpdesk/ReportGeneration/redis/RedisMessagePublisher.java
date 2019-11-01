package com.helpdesk.ReportGeneration.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.ReportGeneration.model.User;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisMessagePublisher implements MessagePublisher {

	@Autowired
	@Qualifier("userBean")
	private RedisTemplate redisTemplate;

//	public RedisMessagePublisher (RedisTemplate redisTemplate){
//		this.redisTemplate = redisTemplate;
//	}

	public void publish(User user, String channelName) throws JsonProcessingException, JSONException {
		ObjectMapper objectMapper = new ObjectMapper();
		String userString = objectMapper.writeValueAsString(user);
		System.out.println("sender = " + user.getSender());
		redisTemplate.convertAndSend(channelName, userString);
		System.out.println("publishing in channel on query coming from socket server " + channelName);
	}
}