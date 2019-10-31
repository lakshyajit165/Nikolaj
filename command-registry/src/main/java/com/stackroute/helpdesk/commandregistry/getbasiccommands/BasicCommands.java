package com.stackroute.helpdesk.commandregistry.getbasiccommands;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/commandregistry")
public class BasicCommands {

    private HashMap<String,Object> responseObject;

    public <T> T checkForBasicCommands(){
        return (T)new ResponseEntity<>("",HttpStatus.OK);
    }


    @GetMapping("docs")
    public<T> T getBasicCommands(){
        RestTemplate restTemplate =new RestTemplate();
        return (T)restTemplate.getForObject("http://zuul-api-gateway:8765/command-framework/api/v1/commandframework/basic-commands",Object.class);
//        return (T)restTemplate.getForObject("http://localhost:8080/basic-commands",Object.class);
    }
}
