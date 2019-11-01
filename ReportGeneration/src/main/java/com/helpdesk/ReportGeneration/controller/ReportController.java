package com.helpdesk.ReportGeneration.controller;

import com.helpdesk.ReportGeneration.Repository.ReportDao;
import com.helpdesk.ReportGeneration.entity.Report;
import com.helpdesk.ReportGeneration.entity.Service;
import com.helpdesk.ReportGeneration.service.CsrReliabilityInterface;
import com.helpdesk.ReportGeneration.service.ReportInterface;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ReportController {

    HashMap<String, Object> responseObject;

    @Autowired
    ReportInterface reportInterface;

    @Autowired
    CsrReliabilityInterface csrReliabilityInterface;

    //to save the data in the database , just for initial requirement
    @PostMapping(path = "/reports")
    public ResponseEntity<HashMap<String, Object>> addReport(@RequestBody Report report) {

        String uuid = UUID.randomUUID().toString().substring(8);
        report.setId(uuid);
        reportInterface.saveReport(report);
        responseObject = new HashMap<>();
        responseObject.put("result", report);
        responseObject.put("message", "Success!");
        responseObject.put("error", "false");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    //to get the service reports
    @GetMapping(path = "/report/service")
    public ResponseEntity<HashMap<String, Object>> getServiceReport(@RequestParam(value = "startdate", required = false) String startDate,
                                                                    @RequestParam(value = "enddate", required = false) String endDate) throws ParseException {

        List<Service> serviceReport = new ArrayList<>();

        if(startDate!= null && !startDate.isEmpty() && endDate!= null && !endDate.isEmpty())
        {
            System.out.println("*************** yahan aaya 1");
            serviceReport = reportInterface.getDataforService(startDate,endDate);
        }
        else if(startDate == "" && endDate!= null && !endDate.isEmpty())
        {
            System.out.println("*************** yahan aaya 2");
            serviceReport = reportInterface.getDataforService("",endDate);
        }
        else if(endDate =="" && startDate!= null && !startDate.isEmpty())
        {
            System.out.println("*************** yahan aaya 3");
            serviceReport = reportInterface.getDataforService(startDate,"");
        }
        else {
            System.out.println("*************** yahan aaya 4");
            serviceReport = reportInterface.getDataforService();
        }


        responseObject = new HashMap<>();
        responseObject.put("result", serviceReport);
        responseObject.put("error", false);
        responseObject.put("message", "Got the service report");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    //to get the list of open, closed , engaged tickets
    @GetMapping(path="/reports")
    public ResponseEntity<HashMap<String, Object>> getReportsByStatus(@RequestParam(value = "status", required = false) String status,
                                                                      @RequestParam(value = "page", required = false) Integer page,
                                                                      @RequestParam(value = "limit", required = false) Integer limit){
        List<Report> result = new ArrayList<>();
        if(status != null && !status.isEmpty()) {
            result = reportInterface.getReportsByStatus(status,(page !=null) ? page : 0,(limit !=null) ? limit : 10);
        }
        else {
            result = reportInterface.getReportsByStatus((page !=null) ? page : 0,(limit !=null) ? limit : 10);
        }
        responseObject = new HashMap<>();
        responseObject.put("result", result);
        responseObject.put("errors", false);
        responseObject.put("message", "Got the reports!");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    //for bot reliability report
    @GetMapping(path="/report/reliablebot")
    public ResponseEntity<HashMap<String, Object>> getAssignTimeResolved(@RequestParam(value = "startdate", required = false) String startDate,
                                                                         @RequestParam(value = "enddate", required = false)String endDate) throws ParseException {
        responseObject = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'",
                Locale.ENGLISH);
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<JSONObject> result = new ArrayList<>();
        result.add(reportInterface.getAllQuery(start,end));
        result.add(reportInterface.getAvgRating(start,end));
        result.add(reportInterface.getTicketsReopen(start,end));
        responseObject.put("result", result);
        responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    //for csr reliabilty report
    @GetMapping(path="/reports/reliablecsr")
    public ResponseEntity<HashMap<String, Object>> getReliableCSR(@RequestParam(value = "month", required = false) Integer month) throws ParseException {

        csrReliabilityInterface.getMonth(month);
        responseObject = new HashMap<>();
        responseObject.put("result",csrReliabilityInterface.putHashMap().entrySet().toArray());
        responseObject.put("errors",false);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    //to get the size of list of open, closed , engaged tickets
    @GetMapping(path="/reportsize")
    public ResponseEntity<HashMap<String, Object>> getSizeofReportsByStatus(@RequestParam(value = "status", required = false) String status){

        int size = 0;
        if(status != null && !status.isEmpty()) {
            size = reportInterface.getReportsByStatus(status);
        }
        else {
            size = reportInterface.getReportsByStatus(null);
        }

        responseObject = new HashMap<>();
        responseObject.put("result",size);
        responseObject.put("errors", false);
        responseObject.put("message", "Got the report size!");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    //to save the data in the database , just for initial requirement
    @PostMapping(path = "/dummy")
    public ResponseEntity<HashMap<String, Object>> addDummyReport(@RequestBody List<Report> object) {
        List<Report> report = (List<Report>) object;
        System.out.println("report = wa" + report);
        reportInterface.saveDummyReports(report);
        responseObject = new HashMap<>();
        responseObject.put("result", report);
        responseObject.put("message", "Success!");
        responseObject.put("error", "false");
        System.out.println(responseObject);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


}
