package com.stackroute.helpdesk.nointentnocommand.messaging;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Message sender to send message to queue using exchange.
 */
@Component
public class MessageSender {
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);
    /**
     *
     * @param rabbitTemplate
     * @param exchange
     * @param routingKey
     * @param data
     */
    public void sendMessage(RabbitTemplate rabbitTemplate, String exchange, String routingKey, JSONObject data) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        String result=objectMapper.writeValueAsString(data);
        MessagingResponse messagingResponse = new MessagingResponse(
                "optimus",
                result,
                exchange,
                routingKey,
                "app-1-queue"
        );
        System.out.println("routing key = "+routingKey);
        System.out.println("data = "+data);
        rabbitTemplate.convertAndSend(  exchange, routingKey, messagingResponse);
        System.out.println("The message has been sent to the queue.");
    }
}