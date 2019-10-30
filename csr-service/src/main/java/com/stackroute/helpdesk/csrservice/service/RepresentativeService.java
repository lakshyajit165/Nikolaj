package com.stackroute.helpdesk.csrservice.service;

import com.stackroute.helpdesk.csrservice.entity.CsrStructure;
import com.stackroute.helpdesk.csrservice.repository.CsrRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Service
public class RepresentativeService implements CsrInterface{


    MongoTemplate mongoTemplate;
    private MongoOperations operations;

    @Autowired
    private CsrRepository csrRepository;

    @Autowired
    public RepresentativeService(MongoTemplate mongoTemplate, MongoOperations operations) {
        super();
        this.mongoTemplate = mongoTemplate;
        this.operations = operations;
    }


    @Override
    public List<CsrStructure> getAllCsr() {
        return csrRepository.findAll();
    }

    @Override
    public void saveCsr(CsrStructure csrStructure) {
        this.csrRepository.save(csrStructure);
    }

    @Override
    public CsrStructure getCsrById(String id) {
        return csrRepository.findByUuid(id);
    }

    @Override
    public JSONObject noOfTickets(String email, int status) {

        List<String> s;

        if(status == 3){
            String[] stats = { "closed" };
            s = Arrays.asList(stats);
        }else{
            String[] stats = { "open", "bot", "engaged", "closed", "callback"};
            s = Arrays.asList(stats);
        }


        GroupOperation groupOperation = group("createdOn").count().as("total");
        MatchOperation filterState =  match((Criteria.where("csrmail").in(email).exists(true)).andOperator(Criteria.where("ticketStatus").in(s)));
        Aggregation aggregation = newAggregation(filterState,
                groupOperation,project("total").and("_id").as("timestamp").andExclude("_id"));

        AggregationResults<CsrStructure> result = mongoTemplate.aggregate(aggregation,"csr", CsrStructure.class);

        return new JSONObject(result.getRawResults());

    }

}
