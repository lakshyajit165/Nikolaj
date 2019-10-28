package com.stackroute.helpdesk.service;
import com.stackroute.helpdesk.entity.TicketStructure;
import com.stackroute.helpdesk.repository.TicketRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.List;

@Service
public class TicketService implements TicketInterface {

    String status;
    MongoTemplate mongoTemplate;
    private MongoOperations operations;


    @Autowired
    private TicketRepository ticketRepository;


    @Autowired
    public TicketService(MongoTemplate mongoTemplate, MongoOperations operations) {
        super();
        this.mongoTemplate = mongoTemplate;
        this.operations = operations;
    }

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

    @Override
    public JSONObject noOfQueryTaken(String csrMail){

        MatchOperation matchOperation = match(Criteria.where("assignedTo").is(csrMail).exists(true)
                .orOperator(Criteria.where("resolvedBy").is(csrMail).exists(true)));

        GroupOperation groupOperation = group("assignedTime").count().as("total");

        Aggregation aggregation = newAggregation(matchOperation,
                groupOperation,project("total").and("_id").as("totalCount").andExclude("_id"));

        //System.out.println("***********" + aggregation);
        AggregationResults<TicketStructure> result = mongoTemplate.aggregate(aggregation,"tickets", TicketStructure.class);

        System.out.println("this is result of aggregate for query taken" + result.getRawResults());
        System.out.println("this is result of aggregate for query taken" + new JSONObject(result.getRawResults()).toJSONString());
        return new JSONObject(result.getRawResults());
    }

    @Override
    public JSONObject noOfQueryResolved(String csrMail){

        MatchOperation matchOperation = match(Criteria.where("status").is("closed")
                .andOperator(Criteria.where("resolvedBy").is(csrMail).exists(true)));

        GroupOperation groupOperation = group("assignedTime").count().as("total");

        Aggregation aggregation = newAggregation(matchOperation,
                groupOperation,project("total").and("_id").as("totalCount").andExclude("_id"));
        //System.out.println("***********" + aggregation);

        AggregationResults<TicketStructure> result = mongoTemplate.aggregate(aggregation,"tickets", TicketStructure.class);

        System.out.println("this is result of aggregate for query taken" + result.getRawResults());
        System.out.println("this is result of aggregate for query taken" + new JSONObject(result.getRawResults()).toJSONString());

        return new JSONObject(result.getRawResults());
    }

}
