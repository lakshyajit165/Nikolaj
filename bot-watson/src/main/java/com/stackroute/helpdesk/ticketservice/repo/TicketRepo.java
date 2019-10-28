package com.stackroute.helpdesk.ticketservice.repo;

import com.stackroute.helpdesk.ticketservice.model.TicketModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepo extends MongoRepository<TicketModel,String> {
    public TicketModel findByUuid(UUID id);
}
