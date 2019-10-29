package com.stackroute.helpdesk.sockets.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.model.User;
import com.stackroute.helpdesk.redis.ChannelHandler;
import com.stackroute.helpdesk.redis.RedisMessagePublisher;
import com.stackroute.helpdesk.sockets.model.SocketStore;
import com.stackroute.helpdesk.sockets.redisrepo.ISocketIdRepo;
<<<<<<< HEAD
import com.stackroute.helpdesk.sockets.service.ChatStoreService;
=======
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
import com.stackroute.helpdesk.sockets.service.SocketIdService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin("*")
public class RecieveMessages {

    @Autowired
    SocketIdService socketIdService;

    @Autowired
<<<<<<< HEAD
    private ChatStoreService chatStoreService;

    @Autowired
=======
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    private ISocketIdRepo iSocketIdRepo;

    private RedisMessagePublisher redisMessagePublisher;

    @Autowired
    public RecieveMessages(RedisMessagePublisher redisMessagePublisher){
        System.out.println(redisMessagePublisher);
        this.redisMessagePublisher = redisMessagePublisher;
    }

    private String socketId;

    public String getSocketId() {
        return socketId;
    }

    private SendMessages sendMessages;

    @Autowired
    ChannelHandler channelHandler;

    @MessageMapping("/send/message")
    public Map<String, String> broadcastNotification(String message) throws JsonProcessingException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(message, Map.class);
            User user = new User();
            user.setEmailId(messageConverted.get("emailId"));
            user.setContent(messageConverted.get("content"));
<<<<<<< HEAD
            System.out.println("storing chat");
            chatStoreService.updateChatHistory(messageConverted.get("content"), "user", "user", messageConverted.get("emailId"));
=======
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
            Optional<SocketStore> socketStore = iSocketIdRepo.findById(messageConverted.get("emailId"));
            SocketStore socketStore1 = null;
            String csrEmailId = "";
            if(socketStore.isPresent()) {
                socketStore1  = socketStore.get();
                csrEmailId = socketStore1.getCsrEmailId();
            }
            if(!csrEmailId.isEmpty()) {
                if (csrEmailId.contains("com")) {
                    user.setSender(socketStore1.getCsrEmailId());
                    redisMessagePublisher.publish(user, "csr_message");
                    System.out.println("inside csr");
                }
                else {
                    redisMessagePublisher.publish(user, "bot_message");
                    System.out.println("inside bot");
                }
            }
            System.out.println("csr email id = " + csrEmailId);
        } catch (IOException e) {
            messageConverted = null;
        }
        return messageConverted;
    }

    @MessageMapping("/send/socketid")
<<<<<<< HEAD
//    @SendTo("/send/socketid")
=======
    @SendTo("/send/socketid")
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    public Map<String, String> getSocketId(String message){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(message, Map.class);
            socketId = messageConverted.get("socketId");
            SocketStore socketStore = new SocketStore();
            socketStore.setEmailId(messageConverted.get("emailId"));
            socketStore.setSocketId(socketId);
            String csrEmail = socketIdService.getCsrEmailId(messageConverted.get("emailId"));
//            if(csrEmail.contains("com")){
              if(!csrEmail.isEmpty()) {
                System.out.println("csr email id = " + csrEmail);
                socketStore.setCsrEmailId(csrEmail);
              }
            else {
                socketStore.setCsrEmailId("bot");
            }
            socketIdService.saveSocket(socketStore);
        } catch (IOException e) {
            System.out.println("error");
            messageConverted = null;
        }
        return messageConverted;
    }

}
