package com.stackroute.helpdesk.userRequest.config;

import com.stackroute.helpdesk.userRequest.controller.ChatController;
import com.stackroute.helpdesk.userRequest.model.ChatMessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Value;
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan("com.stackroute.helpdesk.userRequest")
@EnableRedisRepositories(basePackages = "com.stackroute.helpdesk.userRequest.repo")
public class Redisconfig {

<<<<<<< HEAD
=======
    @Value("${REDIS_HOST}")
    private String redisHostName;
    @Value("${REDIS_PORT}")
    private String redisPortNum;

>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
<<<<<<< HEAD
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(6379);
=======
        jedisConFactory.setHostName(redisHostName);
        jedisConFactory.setPort(Integer.parseInt(redisPortNum));
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
        return jedisConFactory;
    }
}
