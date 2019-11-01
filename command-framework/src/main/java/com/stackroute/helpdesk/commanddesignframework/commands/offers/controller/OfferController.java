package com.stackroute.helpdesk.commanddesignframework.commands.offers.controller;
import com.stackroute.helpdesk.commanddesignframework.commands.offers.model.Campaign;
import com.stackroute.helpdesk.commanddesignframework.commands.offers.service.OfferService;
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
import java.util.List;

@RestController
public class OfferController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OfferService offerService;
    @GetMapping("/alloffers")
    public ResponseEntity<Object> getAllOffers(){
        ResponseEntity<Object> jsonObject = restTemplate.getForEntity("http://umove-dev.stackroute.io:8095/api/v1/campaigns", Object.class);
//        System.out.println(jsonObject.getBody());
//        List<Campaign> campaignList = (List<Campaign>) (((LinkedHashMap)jsonObject.getBody()).get("data"));
        ArrayList<String> resultList = new ArrayList<>();
//        ((List<Campaign>) jsonObject.getBody()).forEach(campaign -> {
            String result = "Get 50% on rides from 23 November 2019 to 31 December 2019";
            resultList.add(result);
//    });
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}