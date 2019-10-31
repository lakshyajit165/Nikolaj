package com.stackroute.helpdesk.commandregistry.invoker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/commandregistry")
public class Invoker {

    static String paramString = "";

//    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/execute/{commandString}")
    public <T> T execute(@PathVariable("commandString") String commandString) {
        restTemplate = new RestTemplate();
        String[] command_string = commandString.split(" ");
        String command = command_string[0];
        String parameters = "";
        if(command_string.length > 1)
        parameters = createParameter(command_string[1]);

        String commandexec = "http://localhost:8080/"+command+"?"+parameters;
//        String commandexec = "http://zuul-api-gateway:8765/command-framework/api/v1/commandframework/"+command+"?"+parameters;
        return (T)restTemplate.getForEntity(commandexec,Object.class);
    }

    public String createParameter(String parameterList){
       String[] listOfParameters = parameterList.split(",");
        AtomicInteger index = new AtomicInteger(0);
        Arrays.asList(listOfParameters).stream().forEach((parameter) -> {
            int counter = index.getAndIncrement();
            paramString += "param"+ counter +"="+listOfParameters[counter]+"&";
            System.out.println(paramString);
        });
        return paramString;
    }
}
