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
public class CsrListener {

    @Autowired
    ReportInterface reportInterface;

    @Autowired
    Environment environment;

    HashMap<String, Task> map = new HashMap<>();

    @RabbitListener(queues = "${csr-details.queue.name}")
    public void receiveMessage(final MessagingResponse messagingResponse) throws Exception {

        Object object = messagingResponse.getEventData();
        Object jsonObject1 = ((LinkedHashMap) object).get("result");

        System.out.println("json object is here " + jsonObject1);

        System.out.println("yahan bhi aaarha h ki ni");

        String eventName = messagingResponse.getEventName();

        HashMap<String, Task> map = new HashMap<>();

        map.put(environment.getProperty("eventname3"), new Task() {
            @Override
            public void performSaveOperation() {
                try {

                    Report report = new Report();
                    String superSecretId = (String) ((LinkedHashMap) (jsonObject1)).get("uuid");
                    report = reportInterface.getReportById(UUID.fromString(superSecretId).toString());

                    //for parsing the date that is coming in the timestamp format
                    Long updatedOn = (Long) ((LinkedHashMap) (jsonObject1)).get("updatedOn");
                    report.setUpdatedOn(new Date(updatedOn));

                    //for parsing the date that is coming in the timestamp format
                    Long assignedTime = (Long) ((LinkedHashMap) (jsonObject1)).get("createdOn");
                    report.setAssignedTime(new Date(assignedTime));

                    report.setAssignedTo((String) ((LinkedHashMap) (jsonObject1)).get("csrmail"));

                    String superSecretId1 = (String) ((LinkedHashMap) (jsonObject1)).get("uuid");
                    report.setCsrId(UUID.fromString(superSecretId1).toString());

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

        map.put(environment.getProperty("eventname4"), new Task() {
            @Override
            public void performSaveOperation() {
                try {

                    Report report = new Report();
                    String superSecretId = (String) ((LinkedHashMap) (jsonObject1)).get("uuid");
                    report = reportInterface.getReportById(UUID.fromString(superSecretId).toString());

                    //for parsing the date that is coming in the timestamp format
                    Long responseTime = (Long) ((LinkedHashMap) (jsonObject1)).get("updatedOn");
                    report.setResponseTime(new Date(responseTime));

                    report.setResolvedBy((String) ((LinkedHashMap) (jsonObject1)).get("csrmail"));

                    System.out.println("report is here " + report);
                    reportInterface.saveReport(report);


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

    }
    }
