package com.stackroute.helpdesk.messaging;

import com.stackroute.helpdesk.sockets.redisrepo.ISocketIdRepo;
import com.stackroute.helpdesk.sockets.service.ChatStoreService;
import com.stackroute.helpdesk.sockets.service.SocketIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedHashMap;

/**
 * Message Listener for RabbitMQ
 */
@Service
public class MessageListener {

    private SocketIdService socketIdService;

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "${socketserver-ticket-closed.queue.name}")
    public void receiveMessageForApp1(MessagingResponse recievedObjectInString) throws Exception {
        System.out.println("recieved object in socket server from queue = " + recievedObjectInString.getEventData());
        String emailId = (String) ((LinkedHashMap) recievedObjectInString.getEventData()).get("body");
        socketIdService = new SocketIdService();
//        socketIdService.removeSocket(emailId);
            try {

            } catch (HttpClientErrorException ex) {
                if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                    log.info("Delay...");
                }
            } catch (Exception exception) {
                log.error("Internal server error occurred in API call. Bypassing message requeue {}", exception);
                throw new AmqpRejectAndDontRequeueException(exception);
            }
        }
    }
