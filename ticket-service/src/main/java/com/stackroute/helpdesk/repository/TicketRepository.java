package com.stackroute.helpdesk.repository;

import com.stackroute.helpdesk.entity.TicketStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends MongoRepository<TicketStructure, String> {


    public TicketStructure findByUuid(String id);

    @Query("{ 'status': ?0 }")
    public List<TicketStructure> getTicketsByStatus(String status);
}
