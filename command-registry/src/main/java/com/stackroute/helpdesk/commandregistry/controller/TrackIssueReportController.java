package com.stackroute.helpdesk.commandregistry.controller;
import com.stackroute.helpdesk.commandregistry.entity.ReportDetails;
import com.stackroute.helpdesk.commandregistry.messaging.MessageListenerForNoCommand;
import com.stackroute.helpdesk.commandregistry.messaging.MessagingResponse;
import com.stackroute.helpdesk.commandregistry.repository.ReportRepository;
import com.stackroute.helpdesk.commandregistry.service.ReportService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;
@RestController
@CrossOrigin(origins = "http://localhost:4200/command-registry-angular")
public class TrackIssueReportController {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ReportService reportService;
    @Autowired
    MessageListenerForNoCommand messageListenerForNoCommand;
    TrackIssueReportController(ReportRepository reportRepository)
    {
        this.reportRepository=reportRepository;
    };
    HashMap<String, Object> responseObject;
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
        JSONObject jsonObject= (JSONObject) reportRepository.findByTypeOfReport(type);
        reportService.printJsonObject1(jsonObject);
        System.out.println(reportRepository.findByTypeOfReport(type));
        result = reportRepository.findByTypeOfReport(type);
        jsonObject.put("data",result);
        System.out.println(jsonObject);
        System.out.println("reports " + result);
        responseObject.put("result", result);
        responseObject.put("errors", false);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
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
}