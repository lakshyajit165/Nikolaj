package com.stackroute.helpdesk.ticketservice.service;

import com.stackroute.helpdesk.ticketservice.model.TicketModel;
import com.stackroute.helpdesk.ticketservice.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService implements TicketServiceInterface{

    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public void saveTicket(TicketModel ticketStructure){
        ticketRepo.save(ticketStructure);
    }

    @Override
    public TicketModel getTicketById(UUID id){
        return ticketRepo.findByUuid(id);
    }
}
