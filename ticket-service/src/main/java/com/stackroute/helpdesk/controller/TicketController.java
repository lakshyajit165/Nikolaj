package com.stackroute.helpdesk.controller;

import com.stackroute.helpdesk.entity.ResponseType;
import com.stackroute.helpdesk.entity.Status;
import com.stackroute.helpdesk.entity.TicketStructure;
import com.stackroute.helpdesk.messaging.MessageSender;
import com.stackroute.helpdesk.service.TicketInterface;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class TicketController implements Serializable {



    @Autowired
    private TicketInterface ticketInterface;



    @Autowired
    private RabbitTemplate rabbitTemplate;

    private HashMap<String, Object> responseObject;

    @Autowired
    MessageSender message;



    private static final String RESULT = "result";
    private static final String MESSAGE = "message";
    private static final String ERRORS = "errors";

    @PostMapping(path="/tickets")
    public ResponseEntity<HashMap<String, Object>> saveTicket(@RequestBody TicketStructure ticketStructure){

        TicketStructure newTicketStructure = ticketStructure;
        responseObject = new HashMap<>();

        UUID uuid = UUID.randomUUID();
        // set uuid
        newTicketStructure.setUuid(uuid.toString());

        // if type is query, assign to bot first, else, keep assignedTo field empty
        if(ticketStructure.getType().toString() == "query"){
            newTicketStructure.setAssignedTo("bot");
        }else{
            newTicketStructure.setAssignedTo("");
        }


        // set createdOn
        newTicketStructure.setCreatedOn(new Date());

        // set updatedOn
        newTicketStructure.setUpdatedOn(new Date());

        // save to database
        ticketInterface.saveTicket(newTicketStructure);

//        JSONObject responseObject = new JSONObject();


        responseObject.put(RESULT, newTicketStructure);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "Ticket created!");
//


        // send message from ticket exchange to csr queue
//        message.sendMessage(rabbitTemplate, "ticket_created", "csr.ticket.requested", ticketStructure, "csr-requested-queue-subscribe");
        if(newTicketStructure.getStatus() == Status.open) {
            message.sendMessage(rabbitTemplate,
                    "ticket_created",
                    "ticket_exchange",
                    "csr.ticket.requested",
                    responseObject,
                    "csr-requested-queue-subscribe");
        }


        // send message from ticket exchange to reports queue
        message.sendMessage(rabbitTemplate,
                "ticket_created",
                "ticket_exchange",
                "reports.ticketstatusreport.generated",
                responseObject,
                "reports-ticketdetails-queue-subscribe");


        System.out.println("message sent outside");

        return new ResponseEntity<HashMap<String, Object>>(responseObject, HttpStatus.OK);
//



    }

    // get ticket by id

    @GetMapping(path="/tickets/{id}")
    public ResponseEntity<HashMap<String, Object>> getTicketById(@PathVariable String id){

        // UUID uuid= UUID.fromString(id);
        responseObject = new HashMap<>();

        TicketStructure ticketStructure = ticketInterface.getTicketById(id);

        responseObject.put(RESULT, ticketStructure);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "Got the ticket with id: "+id);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // Get ticket by status
    @GetMapping(path="/tickets")
    public ResponseEntity<HashMap<String, Object>> getTicketsByStatus(
            @RequestParam String status
    ){
        responseObject = new HashMap<>();

        List<TicketStructure> openTickets = ticketInterface.getTicketsByStatus(status);

        responseObject.put(RESULT, openTickets);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "Got open tickets");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // Update a ticket by id (whenever a csr assigns himself a ticket)
//    @PatchMapping(path="tickets/{id}")
//    public ResponseEntity<HashMap<String, Object>> updateTicket(
//            @PathVariable String id,
//            @RequestParam("status") int status,
//            @RequestParam("assignedTo") String assignedTo
//
//    ){
//        responseObject = new HashMap<>();
//
//        TicketStructure ticketStructure = ticketInterface.getTicketById(id);
//
//        ticketStructure.setAssignedTime(new Date());
//        ticketStructure.setAssignedTo(assignedTo);
//
//        // save to database
//        ticketInterface.saveTicket(ticketStructure);
//
//        responseObject.put(RESULT, ticketStructure);
//        responseObject.put(ERRORS, false);
//        responseObject.put(MESSAGE, "Ticket with id: "+ id + " updated and csr assigned");
//
//
//        // send message from ticket exchange to reports queue
//        message.sendMessage(rabbitTemplate,
//                "ticket_status_udpated",
//                "ticket_exchange",
//                "reports.ticketstatusreport.generated",
//                responseObject,
//                "reports-ticketdetails-queue-subscribe");
//
//
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//
//    }

    // Update a ticket by id (whenever a ticket is closed/resolved) and when
    // a CSR assigns himself

    @PatchMapping(path="tickets/{id}")
    public ResponseEntity<HashMap<String, Object>> updateTicket(
            @RequestBody TicketStructure ticketStructure,
            @PathVariable("id") String id,
            @RequestParam(value = "status") Integer status,
//            @RequestParam(value = "assignedTo", required = false) String assignedTo,
            @RequestParam(value = "resolvedBy", required = false) String resolvedBy,
            @RequestParam(value = "responseType", required = false) Integer responseType
    ){


        TicketStructure newTicketStructure = ticketInterface.getTicketById(id);

        responseObject = new HashMap<>();

        System.out.println("this is Object*******"  + ticketStructure);
        System.out.println("this is status*******"  + Status.values()[status]);
//        newTicketStructure.setStatus(Status.values()[status]);


        if(responseType != null) {
            System.out.println("inside resolved by not equals null");
            // set updatedOn time when ticket is resolved
            newTicketStructure.setAssignedTo(resolvedBy);
            newTicketStructure.setUpdatedOn(new Date());

            message.sendMessage(rabbitTemplate,
                    "ticket_closed",
                    "ticket_exchange",
                    "socketserver.ticket.closed",
                    newTicketStructure,
                    "socketserver-closed-queue-subscribe");


            message.sendMessage(rabbitTemplate,
                    "ticket_updated",
                    "ticket_exchange",
                    "notification.mail.sent",
                    newTicketStructure,
                    "notification-sent-queue-subscribe");

        }else{

            // update assignedTime
            newTicketStructure.setAssignedTime(new Date());

            System.out.println("inside resolved by null");
            // set updatedOn when csr assigns himself
            newTicketStructure.setUpdatedOn(new Date());

            // send message from ticket exchange to notification



        }

        newTicketStructure.setResolvedBy(resolvedBy);



        System.out.println(status);
        if(status != null )
            newTicketStructure.setStatus(Status.values()[status]);


        if(responseType != null)
            newTicketStructure.setResponseType(ResponseType.values()[responseType]);



        // save to database
        ticketInterface.saveTicket(newTicketStructure);

       // System.out.println(assignedTo);
        System.out.println("this is new updated code+++++++"+ newTicketStructure);
        responseObject.put(RESULT, newTicketStructure);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "Ticket with id: "+ id + " updated!");


        // send message from ticket exchange to csr

        message.sendMessage(rabbitTemplate,
                "ticket_updated",
                "ticket_exchange",
                "csr.csr.updated",
                responseObject,
                "csr-updated-queue-subscribe");

//        System.out.println("message sent outside");

//

//        System.out.println("message sent outside");


        
        // send message to report microservice

        message.sendMessage(rabbitTemplate,
                "ticket_status_updated",
                "ticket_exchange",
                "reports.ticketstatusreport.generated",
                responseObject,
                "reports-ticketdetails-queue-subscribe");



        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    // To calculate csr performance

    @GetMapping(path="/tickets/csr/{csrmail}/taken")
    public ResponseEntity<HashMap<String, Object>> getAssignTimeTaken(
            @PathVariable("csrmail") String csrmail){

        responseObject = new HashMap<>();
        responseObject.put("result", ticketInterface.noOfQueryTaken(csrmail));
        responseObject.put("errors", false);

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    @GetMapping(path="/tickets/csr/{csrmail}/solved")
    public ResponseEntity<HashMap<String, Object>> getAssignTimeResolved(
            @PathVariable("csrmail") String csrmail
    ){
        responseObject = new HashMap<>();
        responseObject.put("result", ticketInterface.noOfQueryResolved(csrmail));
        responseObject.put("errors", false);

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }



}
