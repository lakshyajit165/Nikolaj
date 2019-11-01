package com.stackroute.helpdesk.mailservice.messaging;

import com.stackroute.helpdesk.mailservice.mailing.Sender;
import com.stackroute.helpdesk.mailservice.messaging.model.TicketStructure;
import com.stackroute.helpdesk.mailservice.pdfConverter.PdfConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.File;
import java.util.LinkedHashMap;

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
        if(recievedObjectInString.getEventName().contentEquals("command_response_pdf")) {
            String result = (String) ((LinkedHashMap)recievedObjectInString.getEventData()).get("body");
            System.out.println(result);
            try {
                file = convertToPdf(result);
                sender.sendResponseViaEmail("ayush.modi10@gmail.com", "Invoice", "<html>\n" +
                        "<body>\n" +
                        "<h1>Please find the attached pdf for your requested invoices.</h1>\n" +
                        "</body>\n" +
                        "</html>", file, "yes");
            } catch (HttpClientErrorException ex) {
                if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                    log.info("Delay...");
                }
            } catch (Exception e) {
                log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
                throw new AmqpRejectAndDontRequeueException(e);
            }
        }
        else if (recievedObjectInString.getEventName().contentEquals("ticket_updated")) {
            System.out.println("ticket recieved");
            TicketStructure ticket = (TicketStructure) ((LinkedHashMap)recievedObjectInString.getEventData()).get("body");
            System.out.println("ticket raised by = " + ticket.getRaisedBy());
            System.out.println("ticket query by = " + ticket.getQuery());
            System.out.println("ticket Id by = " + ticket.getId());
            System.out.println("ticket status by = " + ticket.getStatus());
            System.out.println("ticket resolved by by = " + ticket.getResolvedBy());

            sender.sendResponseViaEmailWithoutAttachment(ticket.getRaisedBy(), "Update on your issue!", "<html>\n" +
                    "<body>\n" +
                    "<h1>Dear Customer </h1>" +
                    "<p> Your ticket with id " + ticket.getId() + "has been resolved!</p>" +
                    "<h1>Your details are as follows: </h1>" +
                    "<p><strong>Query: </strong>" + ticket.getQuery() + "</p>" +
                    "<p><strong>Status: </strong>" + ticket.getStatus() + "</p>" +
                    "<p><strong>Resolved by:  </strong>" + ticket.getResolvedBy() + "</p>" +
                    "</body>\n" +
                    "</html>", file, "no");
            System.out.println("ticket sent in the mail " + ticket.getQuery());
        }
    }
    public File convertToPdf(String resultObject) throws Exception {
        file = pdfConverter.convertToPdf(resultObject);
        return file;
    }
}
