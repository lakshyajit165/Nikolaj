package com.stackroute.helpdesk.commandregistry.messaging;
//import com.stackroute.helpdesk.mailservice.Sender;
import com.stackroute.helpdesk.commandregistry.trackissueservice.service.ReportService;
import org.json.simple.JSONObject;
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
public class MessageListenerForNoIntent {
    //    @Autowired
//    private Sender sender;
    @Autowired
    ReportService reportService;
    private static final Logger log = LoggerFactory.getLogger(MessageListenerForNoCommand.class);
    @RabbitListener(queues = "${no-intent-report-recieved.queue.name}")
    public void receiveMessageForNoIntentReport(MessagingResponse recievedObjectInJson) throws Exception {
        if(recievedObjectInJson.getEventData() == "")
        System.out.println("event data = " + recievedObjectInJson.getEventData());
        System.out.println("body = " + (LinkedHashMap)recievedObjectInJson.getEventData());
        if((String)(((LinkedHashMap) recievedObjectInJson.getEventData()).get("NoIntent")) == "Well done by developers.Currently all quries have commands"){
            System.out.println("inside if");
        }else {
            System.out.println("inside else");
            JSONObject jsonObject = (JSONObject)((LinkedHashMap)recievedObjectInJson.getEventData()).get("body");
            reportService.printJsonObject1(jsonObject);
        }
//        (LinkedHashMap)((LinkedHashMap) recievedObjectInJson.getEventData()).get()
//        JSONObject jsonObject = (JSONObject)((LinkedHashMap)recievedObjectInJson.getEventData()).get("body");
        try {
            log.info("message added to the no intent queue");
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
