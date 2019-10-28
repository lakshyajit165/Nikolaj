package com.stackroute.helpdesk.csrservice.controller;


import com.stackroute.helpdesk.csrservice.entity.CsrStructure;
import com.stackroute.helpdesk.csrservice.entity.Status;
import com.stackroute.helpdesk.csrservice.entity.TicketStatus;
import com.stackroute.helpdesk.messaging.MessageSender;
import com.stackroute.helpdesk.csrservice.service.CsrInterface;
import com.stackroute.helpdesk.messaging.MessageStructure;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class CsrController {

    private static final String CSR_REQUESTED_QUEUE = "csr-requested-queue-subscribe";


    @Autowired
    private CsrInterface csrInterface;

    private HashMap<String, Object> responseObject;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    MessageSender message;


    private static final String RESULT = "result";
    private static final String MESSAGE = "message";
    private static final String ERRORS = "errors";
    private Object MessageStructure;

    // Executed whenever the csr clicks on Assign me
    @PostMapping(path="/csr")
    public ResponseEntity<HashMap<String, Object>> saveCsr(
            @RequestBody CsrStructure csrStructure,
            @RequestParam("ticketname") String ticketname
    ){

        responseObject = new HashMap<>();

        UUID uuid = UUID.randomUUID();
        // set uuid
        csrStructure.setUuid(uuid.toString());

        // set status to engaged
        csrStructure.setStatus(Status.engaged);

        // set Ticketname(query)
        csrStructure.setTicketName(ticketname);

        // set TicketStatus to engaged
        csrStructure.setTicketStatus(TicketStatus.engaged);

        // set createdOn
        csrStructure.setCreatedOn(new Date());

        // set updatedOn
        csrStructure.setUpdatedOn(new Date());

        csrInterface.saveCsr(csrStructure);

        responseObject.put(RESULT, csrStructure);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "Csr created!");


        // message to reports

        message.sendMessage(rabbitTemplate,
                "csr_ticket_assigned",
                "csr_exchange",
                "reports.csroutlierreport.generated",
                responseObject,
                "reports-csrdetails-queue-subscribe");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // called when ticket is closed or updated(callback or user reported) by the csr

    @PatchMapping("csr/{id}/ticket/{tid}")
    public ResponseEntity<HashMap<String, Object>> updateCsr(
            @RequestBody CsrStructure csrStructure,
            @PathVariable String id,
            @PathVariable String tid,
            @RequestParam("status") int status

    ){
        responseObject = new HashMap<>();

        CsrStructure newCsrStructure = csrInterface.getCsrById(id);


        newCsrStructure.setTicketId(tid);

        // update ticket status
        newCsrStructure.setTicketStatus(TicketStatus.values()[status]);

        // update csr status
        newCsrStructure.setStatus(Status.closed);


        // update database
        csrInterface.saveCsr(newCsrStructure);

        responseObject.put(RESULT, newCsrStructure);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "CSR updated!");


        // temporarily placed here because rating functionality is to be done

        message.sendMessage(rabbitTemplate,
                "csr_ticket_closed",
                "csr_exchange",
                "reports.csroutlierreport.generated",
                responseObject,
                "reports-csrdetails-queue-subscribe");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    @PatchMapping("csr/{id}/ticket/{tid}/rating")
    public ResponseEntity<HashMap<String, Object>> setCsrRating(
            @RequestBody CsrStructure csrStructure,
            @PathVariable String id,
            @PathVariable String tid,
            @RequestParam("rating") int rating
    ){
        responseObject = new HashMap<>();

        CsrStructure newCsrStructure = csrInterface.getCsrById(id);

        newCsrStructure.setRating(rating);

        // save to database;
        csrInterface.saveCsr(newCsrStructure);

        responseObject.put(RESULT, newCsrStructure);
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "CSR rating updated!");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // to get a csr by id
    @GetMapping("csr/{id}")
    public ResponseEntity<HashMap<String, Object>> getCsrPerformance(
            @PathVariable String id,
            @RequestParam("status") int status
    ){
        responseObject = new HashMap<>();
        CsrStructure csr = csrInterface.getCsrById(id);
        responseObject.put("result", csrInterface.noOfTickets(csr.getCsrmail(), status));
        responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("csr")
    public ResponseEntity<HashMap<String, Object>> getAllCsr() {
        responseObject = new HashMap<>();

        List<CsrStructure> csrs = csrInterface.getAllCsr();

        responseObject.put("result", csrs);
        responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    // receiving from queue

    @GetMapping("csr/assign")
    public ResponseEntity<HashMap<String, Object>> assignTicket(){
//        Object message = rabbitTemplate.receiveAndConvert(CSR_REQUESTED_QUEUE);
        System.out.println("inside controller");

        MessageStructure m = (MessageStructure) rabbitTemplate.receiveAndConvert(CSR_REQUESTED_QUEUE);
       // System.out.println("***********" + rabbitTemplate.receiveAndConvert(CSR_REQUESTED_QUEUE)) ;

        //   System.out.println(m.toString());
        System.out.println(m);
        responseObject = new HashMap<>();

        // HashMap<String, Object> result = new HashMap<>();
        //result = (HashMap<String, Object>) m.getEventData();
        //responseObject = m.getEventData();


        //Object result = result.get("result");

        //JSONObject jsonObject = (JSONObject) result.get("result");
        responseObject.put(RESULT, m.getEventData());
        responseObject.put(ERRORS, false);
        responseObject.put(MESSAGE, "ticket assigned!");

        System.out.println(m.getEventData());


        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }
}
