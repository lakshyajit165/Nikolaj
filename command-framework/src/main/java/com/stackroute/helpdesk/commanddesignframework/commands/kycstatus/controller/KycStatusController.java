package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.service.KycStatusService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KycStatusController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KycStatusService kycStatusService;

    @GetMapping("/kycstatus")
    public ResponseEntity<Object> getKycStatus(@RequestParam("param0") String userId){
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        return new ResponseEntity<>(kycStatusService.getKycStatus(jsonObject), HttpStatus.OK);
    }
}
