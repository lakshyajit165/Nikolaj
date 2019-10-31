package com.stackroute.helpdesk.commandregistry.commandstorage.controller;

import com.stackroute.helpdesk.commandregistry.commandstorage.model.CommandDetails;
import com.stackroute.helpdesk.commandregistry.commandstorage.model.Parameter;
import com.stackroute.helpdesk.commandregistry.exceptions.CommandNotFoundException;
import com.stackroute.helpdesk.commandregistry.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class BasicCommands {

    @Autowired
    private CommandRepository commandRepository;

    private HashMap<String,Object> responseObject;

    public <T> T checkForBasicCommands(){
        return (T)new ResponseEntity<>("",HttpStatus.OK);
    }

    @Value("${API_GATEWAY_HOST_PORT}")
    private String hostName;

    @PostMapping("/docs")
    public void getBasicCommands(){
        try{
        RestTemplate restTemplate =new RestTemplate();
        List<CommandDetails> commandDetailsList = new ArrayList<>();
        Object commandListResponse = restTemplate.getForObject(hostName+"commandframework/basic-commands",Object.class);
            System.out.println(((List)commandListResponse).size());
            ((List)commandListResponse).forEach((command) -> {
                System.out.println("command = "+command);
                CommandDetails commandDetails = new CommandDetails();
                commandDetails.setName((String) ((LinkedHashMap)command).get("name"));
                commandDetails.setDescription((String) ((LinkedHashMap)command).get("description"));
                commandDetails.setStatus((String) ((LinkedHashMap)command).get("status"));
                commandDetails.setUsage((String) ((LinkedHashMap)command).get("usage"));
                commandDetails.setTags((List<String>) ((LinkedHashMap)command).get("tags"));
                commandDetails.setParameterList((List<Parameter>) ((LinkedHashMap)command).get("parameterList"));
                commandDetailsList.add(commandDetails);
            });
            System.out.println("list = " + commandDetailsList);
            LinkedHashMap response = (LinkedHashMap)((List)commandListResponse).get(0);

            response.forEach((key, value) -> {
                System.out.println(key);
                System.out.println(value);
            });
//            List<CommandDetails> commandDetailsList = new ArrayList<>();
//            System.out.println(responseEntity);
//            responseEntity.forEach((commandDetails, value) -> {
//                System.out.println("key = "+ commandDetails);
//                System.out.println("value = "+value);
//            });
            //            List<CommandDetails> commandListRecieved = commandListResponse.getBody();
//            commandListRecieved.forEach((command) -> {
//                if(((CommandDetails)command).g=="result"){
//                    HashMap<String,Object> basicCommandList = (HashMap<String,Object>)valueOfCommandList;
//                    List<Commands> listOfCommand = new ArrayList<>();
//                    basicCommandList.forEach((commandName,commandDetails) -> {
//                        if(commandName.charAt(1) != ' '){
//                            Commands command = new Commands();
//                            command.setName(commandName.split("/")[1].substring(0,commandName.split("/")[1].length()-1));
//                            command.setStatus("active");
//                            listOfCommand.add(command);
//                            //System.out.println(commandName);
//                        }
//                    });
                    commandRepository.saveAll(commandDetailsList);
//                }
//            });
//            return ()commandListRecieved;
        //        return (T)restTemplate.getForObject("http://localhost:8080/basic-commands",Object.class);
        }catch (HttpClientErrorException.NotFound exception){
            throw new CommandNotFoundException("command does not exists");
        }
    }
}
