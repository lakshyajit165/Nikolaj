package com.stackroute.helpdesk.commanddesignframework.commands.offers.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.offers.service.OfferService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OfferController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OfferService offerService;

    @GetMapping("/alloffers")
    public ResponseEntity<Object> getAllOffers(@RequestParam("param0") String userId){
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        return new ResponseEntity<>(offerService.getAllOffers(jsonObject), HttpStatus.OK);
    }
}