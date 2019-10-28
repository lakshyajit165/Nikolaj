package com.stackroute.helpdesk.mailservice.messaging;

import com.stackroute.helpdesk.mailservice.Sender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Message Listener for RabbitMQ
 */
@Service
public class MessageListener {

    @Autowired
    private Sender sender;

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "${app2.queue.name}")
    public void receiveMessageForApp1(MessagingResponse recievedObjectInString) throws Exception {
        System.out.println(recievedObjectInString.getEventData());
//        System.out.println(((LinkedHashMap) (recievedObjectInString.getEventData())).get("body"));
        String filePath = (String)((LinkedHashMap)recievedObjectInString.getEventData()).get("body");
        System.out.println(filePath);
//        if (((String) messagingResponse.getEventName()).equalsIgnoreCase("command_response_pdf")) {
            try {
                sender.sendResponseViaEmail("ayush.modi10@gmail.com", "Trial 101111 - rabbit mq mail sending", "<html>\n" +
                        "<body>\n" +
                        "<h1>Trial 101</h1>\n" +
                        "<h2>Rabbit mq with mail service</h2>\n" +
                        "</body>\n" +
                        "</html>", filePath);
            } catch (HttpClientErrorException ex) {
                if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                    log.info("Delay...");
                }
            } catch (Exception e) {
                log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
                throw new AmqpRejectAndDontRequeueException(e);
            }
        }
    }
