package com.stackroute.helpdesk.commanddesignframework.commands.takefeedback.controller;

import com.stackroute.helpdesk.commanddesignframework.pdfConverter.PdfConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@RestController
public class FeedbackController {
    @Autowired
    private PdfConverter pdfConverter;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/takefeedback")
    public ResponseEntity<Object> takeFeedback(@RequestParam("param0") String orderId, @RequestParam("param1") String feedback) throws Exception {
        //JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        String returnString = "feedback has been received for orderId: "+orderId;
        return new ResponseEntity<>(returnString, HttpStatus.OK);
    }
}
