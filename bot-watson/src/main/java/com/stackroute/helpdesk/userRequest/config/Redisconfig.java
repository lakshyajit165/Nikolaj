package com.stackroute.helpdesk.userRequest.config;

import com.stackroute.helpdesk.userRequest.controller.ChatController;
import com.stackroute.helpdesk.userRequest.model.ChatMessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan("com.stackroute.helpdesk.userRequest")
@EnableRedisRepositories(basePackages = "com.stackroute.helpdesk.userRequest.repo")
public class Redisconfig {

    @Value("${REDIS_HOST}")
    private String redisHostName;
    @Value("${REDIS_PORT}")
    private String redisPortNum;

    @Bean
    @DependsOn("jedis")
    @Lazy
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
    @Bean("jedis")
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(redisHostName);
        jedisConFactory.setPort(Integer.parseInt(redisPortNum));
        jedisConFactory.setPassword("nikolaj");
        return jedisConFactory;
    }
}
