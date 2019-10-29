package com.stackroute.helpdesk.nointentnocommand.controller;


import com.stackroute.helpdesk.nointentnocommand.model.Report;
import com.stackroute.helpdesk.nointentnocommand.model.ReportType;
import com.stackroute.helpdesk.nointentnocommand.model.UpdateReport;
import com.stackroute.helpdesk.nointentnocommand.service.ReportServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/optimus/api/v1")
public class ReportController {

    HashMap<String,Object> responseObject;

    @Autowired
    ReportServiceRepo reportServiceRepo;


    @PatchMapping("/report")
    public ResponseEntity<HashMap<String,?>> updateReport(@RequestBody UpdateReport updateReport){
        responseObject = new HashMap<>();
        responseObject.put("result", reportServiceRepo.updateReport(updateReport));
        responseObject.put("message", "Successfully displayed the command report");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }
    @PostMapping("/report")
    public ResponseEntity<HashMap<String,?>> addRecord(@RequestBody Report report){
        responseObject = new HashMap<>();
        responseObject.put("result", reportServiceRepo.addRecord(report));
        responseObject.put("message", "Successfully inserted the command report");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/report")
    public ResponseEntity<HashMap<String,?>> updateRecord(){
        responseObject = new HashMap<>();
        responseObject.put("result", reportServiceRepo.getReport());
        responseObject.put("message", "Successfully inserted the command report");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
