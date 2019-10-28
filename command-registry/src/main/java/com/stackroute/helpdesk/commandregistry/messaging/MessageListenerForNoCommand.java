package com.stackroute.helpdesk.commandregistry.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedHashMap;

/**
 * Message Listener for RabbitMQ
 */
@Service
public class MessageListenerForNoCommand {

//    @Autowired
//    private Sender sender;

    private static final Logger log = LoggerFactory.getLogger(MessageListenerForNoCommand.class);

    @RabbitListener(queues = "${no-command-report-recieved.queue.name}")
    public void receiveMessageForNoCommandReport(MessagingResponse recievedObjectInString) throws Exception {
      recievedObjectInString.getEventData();
      String filePath = (String)((LinkedHashMap)recievedObjectInString.getEventData()).get("body");

           try {
               log.info("message added to the no command queue");


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

