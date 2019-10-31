package com.helpdesk.ReportGeneration.service;

import com.helpdesk.ReportGeneration.Repository.ReportDao;
import com.helpdesk.ReportGeneration.entity.Report;
import com.helpdesk.ReportGeneration.entity.ticketStatus;
import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.data.domain.PageRequest;

@Data
@Service
public class ReportService implements ReportInterface {

    ReportDao reportDao;
    MongoTemplate mongoTemplate;
    private MongoOperations operations;

    @Autowired
    public ReportService(ReportDao reportDao, MongoTemplate mongoTemplate, MongoOperations operations) {
        super();
        this.reportDao = reportDao;
        this.mongoTemplate = mongoTemplate;
        this.operations = operations;
    }

    public List<Report> getAllReports() {
        List<Report> reports = reportDao.findAll();
        return reports;
    }

    @Override
    public List<Report> getReports(int page, int limit) {
        List<Report> reports = (List<Report>) reportDao.findAll(new PageRequest(page, limit)).getContent();
        return reports;
    }

    public void saveReport(Report report) {
        reportDao.save(report);
    }

    //used for messaging , change coz of uuid
    public Report getReportById(String id) {
        return reportDao.findByTicketId(id);
    }


    public List<com.helpdesk.ReportGeneration.entity.Service> getDataforService() throws ParseException {
        List<Report> reports = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime();
        calendar.add(Calendar.DATE,-7);
        Date formerdate = calendar.getTime();
        reports = reportDao.findAllByCreatedOn(formerdate,todayDate);
        return manipulateServiceReport(reports);
    }

    //method to get the service report which is having total no of queries of particular entities and queries solved of particular entity
    public List<com.helpdesk.ReportGeneration.entity.Service> getDataforService(String startDate, String endDate) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime();
        calendar.add(Calendar.DATE,-7);
        Date formerdate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        List<Report> reports = new ArrayList<>();
        //since the date which is coming from angular in some specific format that is yyyy-mm-dd
        if(startDate == "")
        {
            System.out.println("dekha......former date...7 din purani kya ari h" + formerdate);
            startDate = dateFormat.format(formerdate);
        }
        if(endDate == "")
        {
            endDate = dateFormat.format(date);
            System.out.println("dekha......aaj ki date in end date " + endDate);
        }
        String startdateToSend = startDate + "T23:59:00.000+0000";
        String enddateToSend = endDate + "T23:59:00.000+0000";
        reports = getTicketsbyTime(startdateToSend, enddateToSend);
        return manipulateServiceReport(reports);
    }

    public List<com.helpdesk.ReportGeneration.entity.Service> manipulateServiceReport(List<Report> reports) {
        int queriesRaised = 0;
        int queriesResolved = 0;
        long leadtime = 0L;
        long averageLeadtime = 0L;


        List<String> entities = new ArrayList<>();
        List<com.helpdesk.ReportGeneration.entity.Service> serviceReport = new ArrayList<>();

        for (Report report : reports) {
            if (report.getEntity() != null)
                entities.add(report.getEntity());
        }

        Set<String> uniqueEntities = new HashSet<String>(entities);

        System.out.println("unique entities here " + uniqueEntities);

        for (String uniqueEntity : uniqueEntities) {
            List<Long> leadTimeList = new ArrayList<>();
            for (Report report : reports) {
                if (report.getEntity() != null) {
                    if (report.getEntity().equalsIgnoreCase(uniqueEntity)) {
                        queriesRaised++;

                        if (report.getTicketStatus().equals(ticketStatus.closed)) {
                            queriesResolved++;

                            if (report.getUpdatedOn() != null && report.getCreatedOn() != null) {
                                leadtime = report.getUpdatedOn().getTime() - report.getCreatedOn().getTime();
                            }
                            System.out.println("leadtime print  " + leadtime);
                            leadTimeList.add(leadtime);
                            System.out.println("leadtime list print  " + leadTimeList);
                        }
                    }
                    averageLeadtime = (long) leadTimeList.stream().mapToLong(value -> value).average().orElse(0L);
                    System.out.println("avg " + averageLeadtime);
                }
            }
            com.helpdesk.ReportGeneration.entity.Service service = new com.helpdesk.ReportGeneration.entity.Service(uniqueEntity, queriesRaised, queriesResolved, TimeUnit.MILLISECONDS.toHours(averageLeadtime));
            serviceReport.add(service);
            queriesRaised = 0;
            queriesResolved = 0;


        }

        System.out.println("the service port here " + serviceReport);
        return serviceReport;
    }


    //to get the tickets created according to the date created
    public List<Report> getTicketsbyTime(String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date start = simpleDateFormat.parse(startDate);
        Date end = simpleDateFormat.parse(endDate);
        return reportDao.findAllByCreatedOn(start, end);
    }


    @Override
    public List<Report> getReportsByStatus(String status, int page, int limit) {
        List<Report> reports = new ArrayList<>();
        reports = reportDao.findAllByTicketStatus(status, new PageRequest(page, limit)).getContent();
        return reports;
    }


    @Override
    public List<Report> getReportsByStatus(int page, int limit) {
        List<Report> reports = new ArrayList<>();
        reports = reportDao.findAll(new PageRequest(page, limit)).getContent();
        return reports;
    }

    @Override
    public Integer getReportsByStatus(String status) {
        List<Report> reports = new ArrayList<>();
        if (!(status == null) && !status.isEmpty()) {
            reports = reportDao.findAllByTicketStatus(status);
        } else {
            reports = reportDao.findAll();
        }
        return reports.size();
    }


    @Override
    public JSONObject getAllQuery(Date startdate, Date endDate) {
        MatchOperation matchOperation1 = match(Criteria.where("createdOn").gte(startdate).lt(endDate));
        MatchOperation matchOperation = match(Criteria.where("assignedTo").in("bot").exists(true)
                .orOperator(Criteria.where("resolvedBy").in("bot").exists(true)));
        GroupOperation groupOperation = group("entity").count().as("total");
        Aggregation aggregation = newAggregation(matchOperation1, matchOperation, groupOperation, project("total").and("_id").as("service").andExclude("_id"));
        AggregationResults<Report> results = mongoTemplate.aggregate(aggregation, "reports", Report.class);
        return new JSONObject(results.getRawResults());
    }


    @Override
    public JSONObject getAvgRating(Date startdate, Date endDate) {
        MatchOperation filterState1 = match(Criteria.where("createdOn").gte(startdate).lt(endDate));
        MatchOperation filterState = match(Criteria.where("ticketStatus").in("closed").exists(true)
                .andOperator(Criteria.where("resolvedBy").in("bot").exists(true)));
        GroupOperation groupOperation = group("entity").avg("rating").as("total");
        Aggregation aggregation = newAggregation(filterState, filterState1, groupOperation,
                project("total").and("_id").as("service").andExclude("_id"));
        AggregationResults<Report> result = mongoTemplate.aggregate(aggregation, "reports", Report.class);
        return new JSONObject(result.getRawResults());
    }

    @Override
    public JSONObject getTicketsReopen(Date startdate, Date endDate) {
        MatchOperation matchOperation = match(Criteria.where("ticketStatus").in("reopen").exists(true)
                .andOperator(Criteria.where("createdOn").gte(startdate).lt(endDate)));
        MatchOperation matchOperation1 = match(Criteria.where("reopenDate").ne(null).exists(true)
                .andOperator(Criteria.where("resolvedBy").in("bot").exists(true)));
        GroupOperation groupOperation = group("entity").count().as("total");
        Aggregation aggregation = newAggregation(matchOperation, matchOperation1, groupOperation, project("total").and("_id").as("service").andExclude("_id"));
        AggregationResults<Report> result = mongoTemplate.aggregate(aggregation, "reports", Report.class);
        return new JSONObject(result.getRawResults());
    }


}
