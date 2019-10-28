package com.stackroute.helpdesk.service;

import com.stackroute.helpdesk.entity.TicketStructure;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketInterface {

    public void saveTicket(TicketStructure ticketStructure);

    public TicketStructure getTicketById(String id);

    public List<TicketStructure> getTicketsByStatus(String status);

    public JSONObject noOfQueryTaken(String csrMail);

    public JSONObject noOfQueryResolved(String csrMail);
}
