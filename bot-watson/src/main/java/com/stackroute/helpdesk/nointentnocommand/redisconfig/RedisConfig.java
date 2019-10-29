package com.stackroute.helpdesk.nointentnocommand.redisconfig;

<<<<<<< HEAD
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
=======
import org.springframework.beans.factory.annotation.Value;
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
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
<<<<<<< HEAD
=======

    @Value("${REDIS_HOST}")
    private String redisHostName;

>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
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
<<<<<<< HEAD
//        jedisConFactory.setHostName("suggestionsdb");
        jedisConFactory.setHostName("redis");
=======
        jedisConFactory.setHostName(redisHostName);
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
        jedisConFactory.setPort(6379);
        return jedisConFactory;
    }

}
