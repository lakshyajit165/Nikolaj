package com.helpdesk.ReportGeneration.controller;

import com.helpdesk.ReportGeneration.Repository.ReportDao;
import com.helpdesk.ReportGeneration.entity.Report;
import com.helpdesk.ReportGeneration.entity.Service;
import com.helpdesk.ReportGeneration.service.CsrReliabilityInterface;
import com.helpdesk.ReportGeneration.service.ReportInterface;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ReportController {

    HashMap<String, Object> responseObject;

    @Autowired
    ReportInterface reportInterface;

    @Autowired
    ReportDao reportDao;

    @Autowired
    CsrReliabilityInterface csrReliabilityInterface;


    //to save the data in the database , just for initial requirement
    @PostMapping(path = "/reports")
    public ResponseEntity<HashMap<String, Object>> addReport(@RequestBody Report report) {

        String uuid = UUID.randomUUID().toString().substring(8);
        String uuid1 = UUID.randomUUID().toString().substring(8);
        String uuid2 = UUID.randomUUID().toString().substring(8);
        report.setId(uuid);
        report.setTicketId(uuid1);
        report.setCsrId(uuid2);
        reportInterface.saveReport(report);
        responseObject = new HashMap<>();
        responseObject.put("result", report);
        responseObject.put("message", "Success!");
        responseObject.put("error", "false");
        System.out.println(responseObject);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    //to get the service reports
    @GetMapping(path = "/report/service")
    public ResponseEntity<HashMap<String, Object>> getServiceReport(@RequestParam(value = "startdate", required = false) String startDate,
                                                                    @RequestParam(value = "enddate", required = false) String endDate) throws ParseException {

        List<Service> serviceReport = new ArrayList<>();

        if(startDate!= null && !startDate.isEmpty() && endDate!= null && !endDate.isEmpty())
        {
            serviceReport = reportInterface.getDataforService(startDate,endDate);
            System.out.println("pehla padav " + startDate + "............." + endDate);
        }
        else {
            serviceReport = reportInterface.getDataforService();
            System.out.println("default case 1");
        }

        responseObject = new HashMap<>();

        responseObject.put("result", serviceReport);
        responseObject.put("error", false);

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
        System.out.println((page !=null) ? page : 0);
        System.out.println("printing the reports here" + result);
        responseObject.put("result", result);
        responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    //for bot reliability report
    @GetMapping(path="/report/reliablebot")
    public ResponseEntity<HashMap<String, Object>> getAssignTimeResolved(@RequestParam(value = "startdate", required = false) String startDate,
                                                                         @RequestParam(value = "enddate", required = false)String endDate) throws ParseException {
        System.out.println("yahan ...arra h ?");
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
        System.out.println("This is response of the end point "+responseObject);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


    //for csr reliabilty report
    @GetMapping(path="/reports/reliablecsr")
    public ResponseEntity<HashMap<String, Object>> getReliableCSR(@RequestParam(value = "startdate", required = false) String startDate,
                                                                  @RequestParam(value = "enddate", required = false)String endDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'",
                Locale.ENGLISH);
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        csrReliabilityInterface.getStartDates(start);
        csrReliabilityInterface.getEndDates(end);
        responseObject = new HashMap<>();
        responseObject.put("result",csrReliabilityInterface.putHashMap().entrySet().toArray());
        responseObject.put("errors",false);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    //to get the size of list of open, closed , engaged tickets
    @GetMapping(path="/report")
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
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }




}