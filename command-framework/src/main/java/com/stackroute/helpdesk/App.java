package com.stackroute.helpdesk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.TypeVariable;
//import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@RefreshScope
@SpringBootApplication
@EnableEurekaClient
public class App
{
    public int in = 2;
    public static void main( String[] args ) throws ClassNotFoundException {
        SpringApplication.run(App.class,args);

//Get Type parameters (generics)

    }
}
