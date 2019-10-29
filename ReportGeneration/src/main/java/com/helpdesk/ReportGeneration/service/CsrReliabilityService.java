package com.helpdesk.ReportGeneration.service;
import com.helpdesk.ReportGeneration.Repository.ReportDao;
import com.helpdesk.ReportGeneration.entity.Csr;
import com.helpdesk.ReportGeneration.entity.Report;
import com.helpdesk.ReportGeneration.entity.ticketStatus;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
@Data
@Service
public class CsrReliabilityService implements CsrReliabilityInterface {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private MongoOperations operations;
    private Csr csr;
    private HashMap<String, Csr> basicMunged;
    private Set<String> uniqueAssignedTo;
    private int month;
    CsrReliabilityService() {
        this.basicMunged = new HashMap<>();
    }
    public void getMonth(Integer month){
        this.month = month;
    }
    private Set<String> getUniqueCSR() {
        List<String> assignedTo = new ArrayList<>();
        for (Report report : reportDao.findByCreatedOn(month)) {
            if (!(report.getAssignedTo().equalsIgnoreCase("bot"))) {
                if (report.getAssignedTo() == report.getResolvedBy()) {
                    assignedTo.add(report.getAssignedTo());
                } else {
                    assignedTo.add(report.getAssignedTo());
                    assignedTo.add(report.getResolvedBy());
                }
            }
        }
        uniqueAssignedTo = new HashSet<String>(assignedTo);
        uniqueAssignedTo.remove(null);
        uniqueAssignedTo.remove("bot");
        return uniqueAssignedTo;
    }
    private int getTotalQueryTaken(String i) {
        int queryTaken = 0;
        for (Report report : reportDao.findByCreatedOn(month)) {
            if(report.getAssignedTo().equalsIgnoreCase(i) || report.getResolvedBy().equalsIgnoreCase(i) ) {
                queryTaken = queryTaken + 1;
            }
        }
        return queryTaken;
    }
    private double getAvgRating(String i) {
        double avgRating = 0;
        MatchOperation matchOperation = match(Criteria.where("resolvedBy").in(i).exists(true)
                .andOperator(Criteria.where("ticketStatus").in("closed").exists(true)));
        GroupOperation groupOperation = group(i).avg("rating").as("total");
        Aggregation aggregation = newAggregation(matchOperation, groupOperation, project("total").and("_id").as("avgRating").andExclude("_id"));
        AggregationResults<Report> result = mongoTemplate.aggregate(aggregation, "reports", Report.class);
        JSONObject trial = new JSONObject(result.getRawResults());
        List<Document> trial1 = (List<Document>) (trial.get("results"));
        if (!(trial1.size() == 0)) {
            avgRating = (double) trial1.get(0).get("total");
        }
        return avgRating;
    }
    private long getCounterTime(String i) {
        List<Long> counterTimeList = new ArrayList<>();
        long averageCounterTime = 0l;
        for (Report report : reportDao.findByCreatedOn(month)) {
            if (report.getAssignedTo().equalsIgnoreCase(i)) {
                if (report.getTicketStatus() == ticketStatus.closed) {
                    long counterTime = 0l;
                    Timestamp timestamp1 = new Timestamp(report.getResponseTime().getTime());
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(timestamp1.getTime());
                    Timestamp timestamp2 = new Timestamp(report.getAssignedTime().getTime());
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTimeInMillis(timestamp1.getTime());
                    counterTime = timestamp1.getTime() - timestamp2.getTime();
                    long seconds = (int) counterTime / 1000;
                    long minutes = (seconds % 3600) / 60;
                    long hours = ((seconds / 3600) * 60);
                    seconds = (((seconds % 3600) % 60) / 60);
                    long clubbedTime = (seconds + minutes + hours);
                    counterTimeList.add(clubbedTime);
                }
            }
        }
        averageCounterTime = (long) counterTimeList.stream().mapToLong(value -> value).average().orElse(0L);
        return averageCounterTime;
    }
    private int getQueryResolved(String i) {
        int queryResolved = 0;
        for (Report report : reportDao.findByCreatedOn(month)) {
            if(report.getAssignedTo().equals(report.getResolvedBy())) {
                if((report.getTicketStatus().equals(ticketStatus.closed)) &&(report.getAssignedTo().contentEquals(i))){
                    queryResolved = queryResolved+1;
                }
            }
        }
        return queryResolved;
    }
    private int getRepoenTicket(String i) {
        int reopenTickets = 0;
        MatchOperation matchOperation2 = match(Criteria.where("createdOn"));
        MatchOperation matchOperation = match(Criteria.where("ticketStatus").is("reopen")
                .andOperator(Criteria.where("reopenDate").ne(null).exists(true)));
        MatchOperation matchOperation1 = match(Criteria.where("resolvedBy").ne("assignedTo")
                .andOperator(Criteria.where("resolvedBy").is(i)));
        GroupOperation groupOperation = group(i).count().as("total");
        Aggregation aggregation = newAggregation(matchOperation, matchOperation1,matchOperation2, groupOperation, project("total").and("_id").as("queriesResolved").andExclude("_id"));
        AggregationResults<Report> result = mongoTemplate.aggregate(aggregation, "reports", Report.class);
        JSONObject trial = new JSONObject(result.getRawResults());
        List<Document> trial1 = (List<Document>) (trial.get("results"));
        if (!(trial1.size() == 0)) {
            reopenTickets = (int) trial1.get(0).get("total");
        }
        return reopenTickets;
    }
    private int maxQueryResolved() {
        int maxNoOfQueries = 0;
        List<Integer> noOfQueries = new ArrayList<>();
        List<Csr> list = new ArrayList<>();
        Iterator queryIterator = basicMunged.entrySet().iterator();
        while (queryIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) queryIterator.next();
            list.add((Csr) mapElement.getValue());
        }
        System.out.println("This is list "+list);
        list.forEach(query -> {
            noOfQueries.add(query.getTotalQueryTaken());
        });
        System.out.println("no of queries"+noOfQueries);
        if (noOfQueries.size() != 0) {
            maxNoOfQueries = Collections.max(noOfQueries);
            System.out.println("assigned"+maxNoOfQueries);
        } else{
            System.out.println("Not able to calculate");
        }
        System.out.println("this is max no of  queries" + maxNoOfQueries);
        return maxNoOfQueries;
    }
    private double getSuccessRate(int queryTaken, int queryResolved) {
        double successCalculator = 0.0;
        double modiifedSuccessCalculator = 0.0;
        try {
            successCalculator = ((double)queryResolved / (double)queryTaken);
            modiifedSuccessCalculator = successCalculator*10;
        } catch (Exception e) {
            System.out.println("division by zero exception in success rate");
        }
        return modiifedSuccessCalculator;
    }
    private double normalizedQueryTaken(int queryTaken, int maxQueries) {
        double normalizedQueryNo = 0.0;
        try {
            normalizedQueryNo = ((double)queryTaken / (double) maxQueries);
            System.out.println("no of normalized queries"+normalizedQueryNo);
        } catch (Exception e) {
            System.out.println("division by zero exception in normalized query");
        }
        return normalizedQueryNo;
    }
    private double efficiencyCalculator(int queryResolved,long counterTime){
        double efficiency = 0.0;
        double modifiedEfficiency = 0.0;
        try {
            efficiency = ((double)queryResolved/(double)counterTime);
            modifiedEfficiency = efficiency*10000.00;
        } catch (Exception e) {
            System.out.println("division by zero exception in efficiency");
        }
        return modifiedEfficiency;
    }

    public HashMap<String,Csr> putHashMap() {
        for (String i : getUniqueCSR()) {
            csr = new Csr();
            csr.setCounterTime(getCounterTime(i));
            csr.setTotalQueryTaken(getTotalQueryTaken(i));
            csr.setAvgRating(getAvgRating(i));
            csr.setTotalQueryResolved(getQueryResolved(i));
            csr.setTotalQueryReopen(getRepoenTicket(i));
            csr.setSuccessRate(getSuccessRate(getTotalQueryTaken(i), getQueryResolved(i)));
            csr.setNormalizedQueriesTaken(normalizedQueryTaken(getTotalQueryTaken(i), maxQueryResolved()));
            csr.setEfficiency(efficiencyCalculator(getQueryResolved(i), getCounterTime(i)));
            basicMunged.put(i, csr);
        }
        System.out.println(basicMunged);
        return basicMunged;
    }
    public String trial(){
        return "message";
    }
}


