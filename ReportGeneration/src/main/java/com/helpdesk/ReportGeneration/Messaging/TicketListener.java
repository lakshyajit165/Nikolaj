package com.helpdesk.ReportGeneration.Messaging;

import com.helpdesk.ReportGeneration.Messaging.Entity.MessagingResponse;
import com.helpdesk.ReportGeneration.Messaging.Entity.Task;
import com.helpdesk.ReportGeneration.entity.Report;
import com.helpdesk.ReportGeneration.service.ReportInterface;
import com.mongodb.util.JSONParseException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

@Service
public class TicketListener {


    @Autowired
    ReportInterface reportInterface;

    @Autowired
    Environment environment;

    HashMap<String, Task> map = new HashMap<>();

    @RabbitListener(queues = "${ticket-details.queue.name}")
    public void receiveMessage(final MessagingResponse messagingResponse) throws Exception {

        Object object = messagingResponse.getEventData();
        Object jsonObject1 = ((LinkedHashMap) object).get("result");

        System.out.println("json object is here " + jsonObject1);

        String eventName = messagingResponse.getEventName();

        HashMap<String, Task> map = new HashMap<>();

        map.put(environment.getProperty("eventname1"), new Task() {
            @Override
            public void performSaveOperation() {
                try {

                    Report report = new Report();
                    String uuid = UUID.randomUUID().toString().substring(25);
                    report.setId(uuid);

                    String superSecretId = (String) ((LinkedHashMap) (jsonObject1)).get("uuid");
                    report.setTicketId(UUID.fromString(superSecretId).toString());
                    report.setTicketName((String) ((LinkedHashMap) (jsonObject1)).get("query"));
                    report.setRaisedBy((String) ((LinkedHashMap) (jsonObject1)).get("raisedBy"));

                    String type = (String) ((LinkedHashMap) (jsonObject1)).get("type");
                    com.helpdesk.ReportGeneration.entity.type type1 = com.helpdesk.ReportGeneration.entity.type.valueOf(type);
                    report.setType(type1);

                    //for parsing the date that is coming in the timestamp format
                    Long createdOn = (Long) ((LinkedHashMap) (jsonObject1)).get("createdOn");
                    report.setCreatedOn(new Date(createdOn));

                    String ticketStatus = (String) ((LinkedHashMap) (jsonObject1)).get("status");
                    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus1 = com.helpdesk.ReportGeneration.entity.ticketStatus.valueOf(ticketStatus);
                    report.setTicketStatus(ticketStatus1);

                    report.setAssignedTime(null);
                    report.setCsrId(null);
                    report.setAssignedTo((String) ((LinkedHashMap) (jsonObject1)).get("assignedTo"));

                    //for parsing the date that is coming in the timestamp format
                    Long updatedOn = (Long) ((LinkedHashMap) (jsonObject1)).get("updatedOn");
                    report.setUpdatedOn(new Date(updatedOn));

                    report.setEntity((String) ((LinkedHashMap) (jsonObject1)).get("entity"));
                    report.setResponseTime(null);

                    String intents = (String) ((LinkedHashMap) (jsonObject1)).get("intent").toString();
                    String intent = intents.substring(1, intents.length() - 1);
                    String delimiter = ",";
                    String intentArray[] = intent.split(delimiter);
                    report.setIntent(intentArray);

                    report.setRating(0);
                    report.setResolvedBy(null);
                    report.setReopenDate(null);
                    System.out.println("report is here " + report);
                    reportInterface.saveReport(report);


                } catch (JSONParseException e) {
                    System.out.println("can't parse json" + e.getMessage());
                } catch (HttpClientErrorException ex) {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        System.out.println("Delay....");
                    }
                } catch (Exception e) {
                    System.out.println("Internal server error occurred in API call. Bypassing message requeue {}" + e);
                    throw new AmqpRejectAndDontRequeueException(e);
                }
            }
        });
        map.put(environment.getProperty("eventname2"), new Task() {
            @Override
            public void performSaveOperation() {

                try {

                    Report report = new Report();
                    String superSecretId = (String) ((LinkedHashMap) (jsonObject1)).get("uuid");
                    System.out.println("helllllllll " + superSecretId);
                    report = reportInterface.getReportById(UUID.fromString(superSecretId).toString());
                    System.out.println("$$$$$$$$$$$$" + report);
                    //changes in ticketStatus and UpdatedOn
                    //for parsing the date that is coming in the timestamp format
                    Long updatedOn = (Long) ((LinkedHashMap) (jsonObject1)).get("updatedOn");
                    report.setUpdatedOn(new Date(updatedOn));

                    String ticketStatus = (String) ((LinkedHashMap) (jsonObject1)).get("status");
                    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus1 = com.helpdesk.ReportGeneration.entity.ticketStatus.valueOf(ticketStatus);
                    report.setTicketStatus(ticketStatus1);

                    System.out.println("report is here " + report);
                    reportInterface.saveReport(report);

                } catch (HttpClientErrorException ex) {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        System.out.println("Delay....");
                    }
                } catch (Exception e) {
                    System.out.println("Internal server error occurred in API call. Bypassing message requeue {}" + e);
                    throw new AmqpRejectAndDontRequeueException(e);
                }

            }
        });
//        ISODate("2019-10-20T16:28:54.307Z"),

        map.put(environment.getProperty("eventname5"), new Task() {
            @Override
            public void performSaveOperation() {
                try {

                    Report report = new Report();
                    String superSecretId = (String) ((LinkedHashMap) (jsonObject1)).get("uuid");
                    report = reportInterface.getReportById(UUID.fromString(superSecretId).toString());

                    Long updatedOn = (Long) ((LinkedHashMap) (jsonObject1)).get("updatedOn");
                    report.setUpdatedOn(new Date(updatedOn));

                    String ticketStatus = (String) ((LinkedHashMap) (jsonObject1)).get("status");
                    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus1 = com.helpdesk.ReportGeneration.entity.ticketStatus.valueOf(ticketStatus);
                    report.setTicketStatus(ticketStatus1);

                    report.setRating((Integer) ((LinkedHashMap) (jsonObject1)).get("rating"));
                    System.out.println("report is here " + report);
                    reportInterface.saveReport(report);

                } catch (HttpClientErrorException ex) {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        System.out.println("Delay....");
                    }
                } catch (Exception e) {
                    System.out.println("Internal server error occurred in API call. Bypassing message requeue {}" + e);
                    throw new AmqpRejectAndDontRequeueException(e);
                }


            }
        });


        map.put(environment.getProperty("eventname6"), new Task() {
            @Override
            public void performSaveOperation() {
                try {

                    Report report = new Report();
                    String superSecretId = (String) ((LinkedHashMap) (jsonObject1)).get("uuid");
                    report = reportInterface.getReportById(UUID.fromString(superSecretId).toString());


                    Long reopenDate = (Long) ((LinkedHashMap) (jsonObject1)).get("reopenTime");
                    report.setUpdatedOn(new Date(reopenDate));

                    report.setResolvedBy((String) ((LinkedHashMap) (jsonObject1)).get("resolvedBy"));

                    System.out.println("report is here " + report);
                    reportInterface.saveReport(report);

                } catch (HttpClientErrorException ex) {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        System.out.println("Delay....");
                    }
                } catch (Exception e) {
                    System.out.println("Internal server error occurred in API call. Bypassing message requeue {}" + e);
                    throw new AmqpRejectAndDontRequeueException(e);
                }


            }
        });


        System.out.println("what the map is showing  " + map.get(eventName));
        map.get(eventName).performSaveOperation();

    }
}