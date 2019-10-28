package com.stackroute.helpdesk.ticketservice.controller;

import com.stackroute.helpdesk.ticketservice.model.ResponseType;
import com.stackroute.helpdesk.ticketservice.model.Status;
import com.stackroute.helpdesk.ticketservice.model.TicketModel;
import com.stackroute.helpdesk.ticketservice.model.Type;
import com.stackroute.helpdesk.ticketservice.service.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("optimus/api/v1/")
public class TicketController {

    @Autowired
    private TicketServiceInterface ticketServiceInterface;

    private HashMap<String, Object> responseObject;

    private static final String RESULT = "result";
    private static final String MESSAGE = "message";
    private static final String ERRORS = "errors";

    @PostMapping(path = "/tickets")
    public ResponseEntity<HashMap<String, Object>> saveTicket(@RequestBody Map map) {
       responseObject = new HashMap<>();
       TicketModel ticket = new TicketModel();
       UUID uuid = UUID.randomUUID();
       // set uuid
       ticket.setUuid(uuid);
       ticket.setRaisedBy((String)map.get("usermail"));
       ticket.setType(Type.valueOf((String)map.get("type")));
       ticket.setQuery((String)map.get("query"));
//        // if type is query, assign to bot first, else, keep assignedTo field empty
//        if (ticket.getType().toString() == "query") {
//            ticket.setAssignedTo("bot");
//        } else {
//            ticket.setAssignedTo("");
//        }
       // set createdOn
       ticket.setCreatedOn(new Date());
       // set updatedOn
       ticket.setUpdatedOn(new Date());
       ticketServiceInterface.saveTicket(ticket);
       responseObject.put(RESULT, ticket);
       responseObject.put(ERRORS, false);
       responseObject.put(MESSAGE, "Ticket created!");
       return new ResponseEntity<>(responseObject, HttpStatus.OK);
   }

    @GetMapping(path="/tickets/{id}")
    public ResponseEntity<HashMap<String, Object>> getTicketById(@PathVariable String id){

        UUID uuid= UUID.fromString(id);
        responseObject = new HashMap<>();

        TicketModel ticket = ticketServiceInterface.getTicketById(uuid);

        responseObject.put(RESULT, ticket);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "Got the ticket with id: "+id);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    @PatchMapping(path="tickets/{id}")
    public ResponseEntity<HashMap<String, Object>> updateTicket(
            @PathVariable String id,
            @RequestParam("status") int status,
            @RequestParam("resolvedBy") String resolvedBy,
            @RequestParam("responseType") int responseType
    ){

        UUID uuid = UUID.fromString(id);
        responseObject = new HashMap<>();

        TicketModel ticket = ticketServiceInterface.getTicketById(uuid);

        ticket.setResolvedBy(resolvedBy);

        ticket.setStatus(Status.values()[status]);

        ticket.setUpdatedOn(new Date());

        ticket.setResponseType(ResponseType.values()[responseType]);

        // save to database
        ticketServiceInterface.saveTicket(ticket);

        responseObject.put(RESULT, ticket);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "Ticket with id: "+uuid + " updated!");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
