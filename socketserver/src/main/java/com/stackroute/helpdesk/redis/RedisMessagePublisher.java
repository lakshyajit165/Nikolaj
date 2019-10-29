package com.stackroute.helpdesk.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.model.User;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisMessagePublisher implements MessagePublisher {

	@Autowired
//	@Qualifier("redisTemp")
	private RedisTemplate redisTemplate;

//	public RedisMessagePublisher (RedisTemplate redisTemplate){
//		this.redisTemplate = redisTemplate;
//	}

	public void publish(User user, String channelName) throws JsonProcessingException, JSONException {
		ObjectMapper objectMapper = new ObjectMapper();
		String userString = objectMapper.writeValueAsString(user);
		System.out.println("publishing in bot_message channel on query coming from socket server confirmed sending email id as " + user.getEmailId());
		redisTemplate.convertAndSend(channelName, userString);
	}
}