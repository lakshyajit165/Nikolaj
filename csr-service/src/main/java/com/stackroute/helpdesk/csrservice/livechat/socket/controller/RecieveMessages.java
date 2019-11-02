package com.stackroute.helpdesk.csrservice.livechat.socket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.csrservice.livechat.model.ChatMessage;
import com.stackroute.helpdesk.csrservice.livechat.model.SocketStore;
import com.stackroute.helpdesk.csrservice.livechat.redis.publisher.RedisPublisher;
import com.stackroute.helpdesk.csrservice.livechat.redis.redisconfig.ChannelHandler;
import com.stackroute.helpdesk.csrservice.livechat.socket.redisrepo.ISocketIdRepo;
import com.stackroute.helpdesk.csrservice.livechat.socket.service.SocketIdService;
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
    private ISocketIdRepo iSocketIdRepo;

    private RedisPublisher redisMessagePublisher;

    @Autowired
    public RecieveMessages(RedisPublisher redisMessagePublisher){
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
            System.out.println("message converted = " + messageConverted);
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setEmailId(messageConverted.get("emailId"));
            chatMessage.setContent(messageConverted.get("content"));
            chatMessage.setSender(messageConverted.get("sender"));
            chatMessage.setType(messageConverted.get("type"));
            chatMessage.setHours(messageConverted.get("hours"));
            chatMessage.setMinutes(messageConverted.get("minutes"));
            redisMessagePublisher.publish(chatMessage);
        } catch (IOException e) {
            messageConverted = null;
        }
        return messageConverted;
    }

    @MessageMapping("/send/socketid")
    @SendTo("/send/socketid")
    public Map<String, String> getSocketId(String message){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(message, Map.class);
            socketId = messageConverted.get("socketId");
            SocketStore socketStore = new SocketStore();
            socketStore.setEmailId(messageConverted.get("emailId"));
            socketStore.setSocketId(socketId);
            socketIdService.saveSocket(socketStore);
        } catch (IOException e) {
            System.out.println("error");
            messageConverted = null;
        }
        return messageConverted;
    }

}
