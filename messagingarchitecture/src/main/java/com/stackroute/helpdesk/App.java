package com.stackroute.helpdesk;

import com.stackroute.helpdesk.messaging.Sender;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * Spring boot application file to make it a spring boot starter/entry point
 *
 */
@SpringBootApplication
@EnableRabbit
@EnableEurekaClient
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
