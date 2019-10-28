package com.stackroute.helpdesk.service;
import com.stackroute.helpdesk.entity.TicketStructure;
import com.stackroute.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements TicketInterface {

    String status;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void saveTicket(TicketStructure ticketStructure){
        ticketRepository.save(ticketStructure);
    }

    @Override
    public TicketStructure getTicketById(String id){
        System.out.println("inside getbyid");
        return ticketRepository.findByUuid(id);
    }

    @Override
    public List<TicketStructure> getTicketsByStatus(String status) {
        return ticketRepository.getTicketsByStatus(status);
    }

}
