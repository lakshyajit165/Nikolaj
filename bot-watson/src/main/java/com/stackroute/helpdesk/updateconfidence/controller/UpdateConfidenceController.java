package com.stackroute.helpdesk.updateconfidence.controller;

import com.stackroute.helpdesk.updateconfidence.model.Feedback;
import com.stackroute.helpdesk.updateconfidence.updateconfidenceservice.UpdateConfidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/optimus/api/v1")
public class UpdateConfidenceController {

    HashMap<String,Object> responseObject;

    @Autowired
    UpdateConfidenceService updateConfidenceService;

    @PatchMapping("/confidence")
    public ResponseEntity<HashMap<String,?>> updateConfidence(@RequestBody Feedback feedback) {
        responseObject = new HashMap<>();
        responseObject.put("result", updateConfidenceService.updateConfidence(feedback));
        responseObject.put("message", "Successfully updated the confidence");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);




    }
}
