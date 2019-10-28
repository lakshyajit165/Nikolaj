package com.stackroute.helpdesk.commanddesignframework.commands.cancelorder.controller;

import com.stackroute.helpdesk.commanddesignframework.pdfConverter.PdfConverter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@RestController
public class CancelOrderController {
    @Autowired
    private PdfConverter pdfConverter;
    @Autowired
    private RestTemplate restTemplate;

    private File file;

    @GetMapping("/cancelorder")
    public ResponseEntity<Object> cancelOrder(@RequestParam("param0") String orderId, @RequestParam("param1") String reason) throws Exception {
        //JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        String returnString = "Order has been cancelled for orderId: "+orderId;



        return new ResponseEntity<>(returnString, HttpStatus.OK);

    }
}
