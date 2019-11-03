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

}
