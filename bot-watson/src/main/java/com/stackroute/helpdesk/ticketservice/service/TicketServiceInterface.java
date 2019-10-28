package com.stackroute.helpdesk.ticketservice.service;

import com.stackroute.helpdesk.ticketservice.model.TicketModel;

import java.util.UUID;

public interface TicketServiceInterface {
    public void saveTicket(TicketModel ticket);

    public TicketModel getTicketById(UUID id);
}
