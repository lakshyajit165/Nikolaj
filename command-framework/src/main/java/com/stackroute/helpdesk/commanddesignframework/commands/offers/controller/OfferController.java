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
@RestController
public class OfferController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OfferService offerService;
    @GetMapping("/alloffers")
    public ResponseEntity<Object> getAllOffers(@RequestParam("param0") String userId){
        ResponseEntity<Campaign> jsonObject = restTemplate.getForEntity("http://umove-dev.stackroute.io:8095/api/v1/campaigns", Campaign.class);
        System.out.println(jsonObject.getBody());
        String result = "Get " + jsonObject.getBody().getDiscountPercent() + "% on " + jsonObject.getBody().getName() +
                " from " + jsonObject.getBody().getStartDate() + " to " + jsonObject.getBody().getEndDate();
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add(result);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}