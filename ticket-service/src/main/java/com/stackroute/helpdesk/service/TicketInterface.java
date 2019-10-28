package com.stackroute.helpdesk.service;

import com.stackroute.helpdesk.entity.TicketStructure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketInterface {

    public void saveTicket(TicketStructure ticketStructure);

    public TicketStructure getTicketById(String id);

    public List<TicketStructure> getTicketsByStatus(String status);
}
