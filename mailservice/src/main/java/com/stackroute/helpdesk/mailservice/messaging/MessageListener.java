package com.stackroute.helpdesk.mailservice.messaging;

import com.stackroute.helpdesk.mailservice.Sender;
import com.stackroute.helpdesk.mailservice.pdfConverter.PdfConverter;
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
import java.io.File;
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

    @Autowired
    private PdfConverter pdfConverter;

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    private File file;

    @RabbitListener(queues = "${app2.queue.name}")
    public void receiveMessageForApp1(MessagingResponse recievedObjectInString) throws Exception {
        System.out.println(recievedObjectInString.getEventData());
        if(recievedObjectInString.getEventName() == "command_response_pdf") {
            String result = (String) ((LinkedHashMap)recievedObjectInString.getEventData()).get("body");
            System.out.println(result);
            try {
                file = convertToPdf(result);
                sender.sendResponseViaEmail("ayush.modi10@gmail.com", "Invoice", "<html>\n" +
                        "<body>\n" +
                        "<h1>Please find the attached pdf for your requested invoices.</h1>\n" +
                        "</body>\n" +
                        "</html>", file);
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
    public File convertToPdf(String resultObject) throws Exception {
        file = pdfConverter.convertToPdf(resultObject);
        return file;
    }
}
