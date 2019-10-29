package com.stackroute.helpdesk.nointentnocommand.redisconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@ComponentScan("com.stackroute.helpdesk.nointentnocommand")
@EnableRedisRepositories(basePackages = "com.stackroute.helpdesk.nointentnocommand.repository")
public class RedisConfig {

    @Value("${REDIS_HOST}")
    private String redisHostName;
    @Value("${REDIS_PORT}")
    private String redisPortNum;

    @Bean
    public RedisTemplate<String, Object> redisTemplateForReport() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactoryForReport());
        return template;
    }
    @Bean
    @Primary
    JedisConnectionFactory jedisConnectionFactoryForReport() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
//        jedisConFactory.setHostName("suggestionsdb");
        jedisConFactory.setHostName(redisHostName);
        jedisConFactory.setPort(Integer.parseInt(redisPortNum));
        return jedisConFactory;
    }

}
