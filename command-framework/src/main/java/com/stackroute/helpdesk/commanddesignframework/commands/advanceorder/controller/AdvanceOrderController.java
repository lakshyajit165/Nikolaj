package com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.controller;

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
public class AdvanceOrderController {
    @Autowired
    private PdfConverter pdfConverter;
    @Autowired
    private RestTemplate restTemplate;

    private File file;

    @GetMapping("/advanceorder")
    public ResponseEntity<Object> advanceOrder() throws Exception {
        //JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        String returnString = "For advance order follow this path on our website: /ordefood/advanceorder";



        return new ResponseEntity<>(returnString, HttpStatus.OK);

    }
}
