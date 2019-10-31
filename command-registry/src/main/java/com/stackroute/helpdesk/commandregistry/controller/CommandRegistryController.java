//package com.stackroute.helpdesk.commandregistry.controller;
//import com.stackroute.helpdesk.commandregistry.entity.Commands;
//
//import com.stackroute.helpdesk.commandregistry.repository.CommandRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.TimeoutException;
//
//@RestController
////@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/api/v1/commandregistry/commands")
//public class CommandRegistryController {
//
//    private static final Logger log = LoggerFactory.getLogger(Commands.class);
//    private final RabbitTemplate rabbitTemplate;
//    private ApplicationConfigReader applicationConfig;
//    private MessageSender messageSender;
//    public ApplicationConfigReader getApplicationConfig() {
//        return applicationConfig;
//    }
//    @Autowired
//    public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
//        this.applicationConfig = applicationConfig;
//    }
//    @Autowired
//    public CommandRegistryController(final RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//    public MessageSender getMessageSender() {
//        return messageSender;
//    }
//    @Autowired
//    public void setMessageSender(MessageSender messageSender) {
//        this.messageSender = messageSender;
//    }
//
//
//    @Autowired
//    private CommandRepository commandRepository;
//
//    CommandRegistryController(RabbitTemplate rabbitTemplate, CommandRepository commandRepository)
//    {
//        this.rabbitTemplate = rabbitTemplate;
//        this.commandRepository=commandRepository;
//    }
//    HashMap<String, Object> responseObject;
//
//    /**
//     *
//     * get all commands
//     *
//     *
//     */
//
//    @GetMapping
//    public ResponseEntity<HashMap<String, Object>> getCommands() {
//
//        List<Commands> commands = commandRepository.findAll();
//
//        responseObject = new HashMap<>();
//        responseObject.put("result", commands);
//        responseObject.put("message", "Success!");
//        responseObject.put("error", "false");
//
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }
//    /**
//     *
//     * add commands
//     *
//     *
//     */
//    @PostMapping
//    public ResponseEntity<HashMap<String,Object>>addCommands(@RequestBody Commands commands) throws IOException, TimeoutException {
//        String exchange1 = getApplicationConfig().getApp1Exchange();
//        String routingKey1 = getApplicationConfig().getApp1RoutingKey();
//
//        //        /* Sending to Message Queue */
//       try {
//            messageSender.sendMessage(rabbitTemplate, exchange1, routingKey1, commands);
//           commandRepository.save(commands);
//           responseObject = new HashMap<>();
//           responseObject.put("result", commands);
//           responseObject.put("message", "Success!");
//           responseObject.put("error", "false");
//           System.out.println(responseObject);
//           return new ResponseEntity<>(responseObject, HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
//           return new ResponseEntity<>(responseObject,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//
////        commandRepository.save(commands);
////        responseObject = new HashMap<>();
////        responseObject.put("result", commands);
////        responseObject.put("message", "Success!");
////        responseObject.put("error", "false");
////        System.out.println(responseObject);
////        return new ResponseEntity<>(responseObject, HttpStatus.OK);
////
//   }
//    /**
//     *
//     * update commands
//     *
//     *
//     */
//    @PatchMapping(consumes={"application/json"})
//   public ResponseEntity<HashMap<String, Object>> patchCommand(@RequestBody Commands commands) {
//        String exchange1 = getApplicationConfig().getApp1Exchange();
//        String routingKey1 = getApplicationConfig().getApp1RoutingKey();
//try{
//    messageSender.sendMessage(rabbitTemplate, exchange1, routingKey1, commands);
//        Commands command1 = commandRepository.findById(commands.getId()).get();
//        command1 = commands;
//        commandRepository.save(command1);
//        responseObject = new HashMap<>();
//        responseObject.put("result", command1);
//        responseObject.put("message", "Success!");
//        responseObject.put("error", "false");
//        return  new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }
//catch (Exception ex){
//    log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
//    return new ResponseEntity<>(responseObject,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        }
//
//
////    String[] str;
////    Commands commands = new Commands("name",str,"desc",new Date(),new Date(), "by","usage", Status.ACTIVE.toString());
//}
