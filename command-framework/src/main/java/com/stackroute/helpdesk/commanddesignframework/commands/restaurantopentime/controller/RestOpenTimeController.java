package com.stackroute.helpdesk.commanddesignframework.commands.restaurantopentime.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.restaurantopentime.services.RestOpenTimeService;
import com.stackroute.helpdesk.commanddesignframework.pdfConverter.PdfConverter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@RestController
public class RestOpenTimeController {
    @Autowired
    private PdfConverter pdfConverter;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestOpenTimeService restOpenTimeService;

    private File file;

    @GetMapping("/restopentime")
    public ResponseEntity<Object> getOpeningTime(@RequestParam("Restaurant Id") String restaurantId) throws Exception {
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        String returnString = restOpenTimeService.getRestOpeningTime(jsonObject, restaurantId);


        return new ResponseEntity<>(returnString, HttpStatus.OK);

    }
}
