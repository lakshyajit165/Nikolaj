package com.stackroute.helpdesk.userRequest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.userRequest.model.ChatMessage;
import com.stackroute.helpdesk.userRequest.service.ChatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Controller
@CrossOrigin
public class ChatController implements MessageListener {

    @Autowired
    private ChatServiceInterface userRequestService;

    @Autowired
    @Qualifier("messagingTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        try {
            ChatMessage chatMessage = objectMapper.readValue(message.getBody(), ChatMessage.class);
            String botResult = userRequestService.postQuery(chatMessage);
            publishReply(chatMessage.getEmailId(), chatMessage.getEmailId() + "_chat_messages", botResult);
            if (!(botResult.contentEquals("Sorry for the inconvenience, we will be connecting you to a customer support representative shortly")) &&
                    !(botResult.contentEquals("Hi I am Optimus, how may I help you ")) &&
                    !(botResult.contentEquals("Hello how can I help you")) &&
                    !(botResult.contentEquals("Sure, how can I help you")) &&
                    !(botResult.contentEquals("How much would you like to rate us"))
            ) {
                Thread.sleep(1000);
                    publishReply(chatMessage.getEmailId(), chatMessage.getEmailId() + "_chat_messages", "Do you have any more queries");

                System.out.println("publishing back from botwatson in channel = " + chatMessage.getEmailId() + "_chat_messages");
            }


        }catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }

    public void publishReply(String emailId, String channelName, String messageToSend) throws JsonProcessingException {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(messageToSend);
        chatMessage.setEmailId(emailId);
        chatMessage.setSender("bot");
        if (messageToSend.equals("How much would you like to rate us"))
            chatMessage.setType("star");
        else
            chatMessage.setType("bot");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("IST"));
        System.out.println("calender hour of day = " + Calendar.HOUR_OF_DAY);
        System.out.println("calender minute  = " + calendar.MINUTE);
        chatMessage.setHours(String.valueOf(calendar.HOUR_OF_DAY));
        chatMessage.setMinutes(String.valueOf(calendar.MINUTE));
        ObjectMapper objectMapper = new ObjectMapper();
        redisTemplate.convertAndSend(channelName, objectMapper.writeValueAsString(chatMessage));
        System.out.println("FROM BOT - published back to the socket server " + channelName);
    }

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//        //System.out.println(chatMessage.getContent());
//        return chatMessage;
//    }
//    @MessageMapping("/chat.botMessage")
//    @SendTo("/topic/public")
//    public ChatMessage botSendMessage(@Payload ChatMessage chatMessage){
//        String botResult = userRequestService.postQuery(chatMessage);
//        System.out.println(botResult);
//        ChatMessage botMessage = new ChatMessage();
//        botMessage.setType("response");
//        botMessage.setSender("Optimus");
//        botMessage.setContent(botResult);
//        return botMessage;
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage,
//                               SimpMessageHeaderAccessor headerAccessor) {
//        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.welcomeFromBot")
//    @SendTo("/topic/public")
//    public ChatMessage welcomeFromBot(@Payload ChatMessage chatMessage) {
//        //sending empty message to trigger welcome intent
//        chatMessage.setContent("");
//        String botResult = userRequestService.postQuery(chatMessage);
//        ChatMessage welcomeMessage = new ChatMessage();
//        welcomeMessage.setType("intiate-chat");
//        welcomeMessage.setSender("Optimus");
//        welcomeMessage.setContent(botResult);
//        return welcomeMessage;
//    }

    public void messageListener(){

    }
}
