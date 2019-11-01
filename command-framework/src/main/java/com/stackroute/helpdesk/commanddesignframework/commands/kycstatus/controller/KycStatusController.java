package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.controller;
import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model.KycStatus;
import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.service.KycStatusService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
public class KycStatusController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KycStatusService kycStatusService;
    @GetMapping("/kycstatus")
    public ResponseEntity<Object> getKycStatus(@RequestParam("param0") String userId){
        ResponseEntity<KycStatus> jsonObject = restTemplate.getForEntity("http://umove-dev.stackroute.io:8091/api/v1/users/"+ userId +"/document", KycStatus.class);
        String result = "Your KYC status is " + jsonObject.getBody().getDocumentStatus() + ".";
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add(result);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}