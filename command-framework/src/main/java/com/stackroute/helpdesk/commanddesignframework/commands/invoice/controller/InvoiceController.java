package com.stackroute.helpdesk.commanddesignframework.commands.invoice.controller;

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

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/lastinvoice")
    public ResponseEntity<Object> getLastInvoice(@RequestParam("param0") String userId, @RequestParam("param1") String bookingStatus){
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        return new ResponseEntity<>(invoiceService.getPreviousInvoices(jsonObject,1), HttpStatus.OK);
    }

    @GetMapping("/previousinvoices")
    public ResponseEntity<Object> getPreviousInvoices(@RequestParam("param0") String userId) throws ClassNotFoundException {
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result",JSONObject.class);
        return new ResponseEntity<>(invoiceService.getPreviousInvoices(jsonObject,10), HttpStatus.OK);
    }

}
