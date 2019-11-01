package com.stackroute.helpdesk.commanddesignframework.commands.invoice.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Payment;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.service.InvoiceService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/lastinvoice")
    public ResponseEntity<Object> getLastInvoice(@RequestParam("param0") String userId){
        ResponseEntity payment = restTemplate.getForEntity("http://umove-dev.stackroute.io:8094/api/v1/rides/payments/" + userId, Object.class);
        List<Payment> paymentList = new ArrayList<>();
        return new ResponseEntity<>(invoiceService.getPreviousInvoices(paymentList,1), HttpStatus.OK);
    }

    @GetMapping("/previousinvoices")
    public ResponseEntity<Object> getPreviousInvoices(@RequestParam("param0") String userId) throws ClassNotFoundException {
        ResponseEntity payment = restTemplate.getForEntity("http://umove-dev.stackroute.io:8094/api/v1/rides/payments/" + userId, Object.class);
        List<Payment> paymentList = new ArrayList<>();
        return new ResponseEntity<>(invoiceService.getPreviousInvoices(paymentList,10), HttpStatus.OK);
    }

}
