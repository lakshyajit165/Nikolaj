package com.stackroute.helpdesk.commanddesignframework.commands.refund.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.refund.model.Invoice;
import com.stackroute.helpdesk.commanddesignframework.commands.refund.services.RefundService;
import com.stackroute.helpdesk.commanddesignframework.pdfConverter.PdfConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RefundController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RefundService refundService;

    @GetMapping("/refund")
    public ResponseEntity<Object> initiateRefund(@RequestParam("param0") String sendToEmail ,@RequestParam("param1") int ticketId,
                                       @RequestParam("param2") int userId) throws Exception {
        String returnString = "your refund is inititated for userId= "+ticketId+"for userId2= "+userId;
        refundService.getInvoice();
        if(sendToEmail.equalsIgnoreCase("true")) {
            List<String> responseList = new ArrayList<>();
            String response = "<html><body><p>"+returnString+"</p></body></html>";
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
        else{
            List<String> responseList = new ArrayList<>();
            responseList.add(returnString);
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
    }
}
