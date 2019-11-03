package com.stackroute.helpdesk.commandregistry.trackissueservice.controller;
import com.stackroute.helpdesk.commandregistry.trackissueservice.entity.ReportDetails;
import com.stackroute.helpdesk.commandregistry.trackissueservice.repository.ReportRepository;

import com.stackroute.helpdesk.commandregistry.trackissueservice.service.NoCommandReport;
import com.stackroute.helpdesk.commandregistry.trackissueservice.service.NoIntentReport;
import com.stackroute.helpdesk.commandregistry.trackissueservice.service.ReportService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class TrackIssueReportController {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ReportService reportService;

    TrackIssueReportController(ReportRepository reportRepository)
    {
        this.reportRepository=reportRepository;
    };
    HashMap<String, Object> responseObject;

    @Autowired
    private NoCommandReport noCommandReport;

    @Autowired
    private NoIntentReport noIntentReport;
    /**
     *
     * get all data for reports
     *
     *
     */



    @GetMapping(path="/api/v1/commandregistry/reports/type")
    public ResponseEntity<HashMap<String, Object>> getReportsByType(@RequestParam("type") String type){
        responseObject = new HashMap<>();
        List<ReportDetails> result = new ArrayList<>();

        result = reportRepository.findByTypeOfReport(type);
        //jsonObject.put("data",result);

//        JSONObject finalResult1 = noCommandReport.formattingReport(type, result);
//        JSONObject finalResult2=noIntentReport.formattingReportIntent(type,result);

        //System.out.println(jsonObject);
//        System.out.println("reports " + result);
        if(type.equals("NoIntent")) {
//            System.out.println("no intent got this data = " + result);
            responseObject.put("result", noIntentReport.formattingReportIntent(type,result));

        }
        else {
            responseObject.put("result", noCommandReport.formattingReportIntent(type, result));

        }
      //  responseObject.put("result", finalResult1);
        //responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


//    @GetMapping(path="/api/v1/commandregistry/reports/type")
//    public ResponseEntity<HashMap<String, Object>> getReportsByType(@RequestParam("type") String type){
//        responseObject = new HashMap<>();
//        List<ReportDetails> result = new ArrayList<>();
//        JSONObject jsonObject= (JSONObject) reportRepository.findByTypeOfReport(type);
//        reportService.printJsonObject1(jsonObject);
//        System.out.println(reportRepository.findByTypeOfReport(type));
//        result = reportRepository.findByTypeOfReport(type);
//        jsonObject.put("data",result);
//
//        System.out.println(jsonObject);
//        System.out.println("reports " + result);
//        responseObject.put("result", result);
//        responseObject.put("errors", false);
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }

    @PostMapping(path="/api/v1/commandregistry/reports")
    public ResponseEntity<HashMap<String,Object>>addReports(@RequestBody ReportDetails reportDetails) throws IOException, TimeoutException {
            reportRepository.save(reportDetails);
            responseObject = new HashMap<>();
            responseObject.put("result", reportDetails);
            responseObject.put("message", "Success!");
            responseObject.put("error", "false");
            System.out.println(responseObject);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }


    @PostMapping(path="/api/v1/commandregistry/reports/updatingdatabse/{command}/{intent}")
    public ResponseEntity<HashMap<String,Object>>updateReportDatabase(@PathVariable String command,@PathVariable String intent) throws IOException, TimeoutException {
        List<ReportDetails> reportDetails=reportRepository.findAll();
        for(ReportDetails report :reportDetails)
        {
            report.getIntent().equals(intent);
        }
        responseObject.put("result", reportDetails);
        responseObject.put("message", "Success!");
        responseObject.put("error", "false");
        System.out.println(responseObject);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


        }



