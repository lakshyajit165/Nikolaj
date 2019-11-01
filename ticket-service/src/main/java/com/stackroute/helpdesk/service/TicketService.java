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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

        HashSet<String> unique=new HashSet<>();
        Map<String,Integer> queryTaken=new HashMap();
        Integer count;
        List<TicketStructure> trial1= ticketRepository.findByAssignedTo(csrMail);
        System.out.println("------------" + trial1);
        for (TicketStructure ticket: trial1){
            String[] format;
           // format = ticket.getassignedTime().toString().split(" ");
            format = ticket.getAssignedTime().toString().split(" ");
            System.out.println("++++++++++++"+ticket.getAssignedTime());
            String date=format[2].concat(format[1].concat(format[5]));
            if(unique.contains(date)){
                count=queryTaken.get(date);
                queryTaken.put(date,count+1);
            }
            else{
                unique.add(date);
                queryTaken.put(date,1);
            }
        }
        return new JSONObject(queryTaken);
    }

    @Override
    public JSONObject noOfQueryResolved(String csrMail){

        HashSet<String> unique=new HashSet<>();
        Map<String,Integer> queryResolved=new HashMap();
        Integer count;
        List<TicketStructure> trial1= ticketRepository.findByResolvedBy(csrMail);
        for (TicketStructure report:trial1){
            String[] format;

            format=report.getAssignedTime().toString().split(" ");

            String date=format[2].concat(format[1].concat(format[5]));
            if(unique.contains(date)){
                count=queryResolved.get(date);
                queryResolved.put(date,count+1);
            }
            else{
                unique.add(date);
                queryResolved.put(date,1);
            }
        }
        return new JSONObject(queryResolved);
    }

//    @Override
//    public JSONObject noOfQueryTaken(String csrMail){
//
//        MatchOperation matchOperation = match(Criteria.where("assignedTo").is(csrMail).exists(true)
//                .orOperator(Criteria.where("resolvedBy").is(csrMail).exists(true)));
//
//        GroupOperation groupOperation = group("assignedTime").count().as("total");
//
//        Aggregation aggregation = newAggregation(matchOperation,
//                groupOperation,project("total").and("_id").as("totalCount").andExclude("_id"));
//
//        //System.out.println("***********" + aggregation);
//        AggregationResults<TicketStructure> result = mongoTemplate.aggregate(aggregation,"tickets", TicketStructure.class);
//
//        System.out.println("this is result of aggregate for query taken" + result.getRawResults());
//        System.out.println("this is result of aggregate for query taken" + new JSONObject(result.getRawResults()).toJSONString());
//        return new JSONObject(result.getRawResults());
//    }
//
//    @Override
//    public JSONObject noOfQueryResolved(String csrMail){
//
//        MatchOperation matchOperation = match(Criteria.where("status").is("closed")
//                .andOperator(Criteria.where("resolvedBy").is(csrMail).exists(true)));
//
//        GroupOperation groupOperation = group("assignedTime").count().as("total");
//
//        Aggregation aggregation = newAggregation(matchOperation,
//                groupOperation,project("total").and("_id").as("totalCount").andExclude("_id"));
//        //System.out.println("***********" + aggregation);
//
//        AggregationResults<TicketStructure> result = mongoTemplate.aggregate(aggregation,"tickets", TicketStructure.class);
//
//        System.out.println("this is result of aggregate for query taken" + result.getRawResults());
//        System.out.println("this is result of aggregate for query taken" + new JSONObject(result.getRawResults()).toJSONString());
//
//        return new JSONObject(result.getRawResults());
//    }

}
