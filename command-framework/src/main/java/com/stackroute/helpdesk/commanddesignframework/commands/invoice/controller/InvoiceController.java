package com.stackroute.helpdesk.commanddesignframework.commands.invoice.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Payment;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.service.InvoiceService;
import com.stackroute.helpdesk.commanddesignframework.commands.offers.model.Campaign;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/lastinvoice")
    public ResponseEntity<Object> getLastInvoice(@RequestParam("param0") String userId){
        ResponseEntity<Object> invoices = restTemplate.getForEntity("http://umove-dev.stackroute.io:8094/api/v1/rides/payments/" + userId, Object.class);
        AtomicReference<List<Payment>> paymentList = new AtomicReference<>();
        ((LinkedHashMap) invoices.getBody()).forEach((object1, object2) -> {
            System.out.println("object1 = " + object1);
            System.out.println("object2 = " + (List<Payment>)object2);
            paymentList.set((List<Payment>) object2);
        });
        return new ResponseEntity<>(invoiceService.getPreviousInvoices(paymentList.get(),1), HttpStatus.OK);
    }

    @GetMapping("/previousinvoices")
    public ResponseEntity<Object> getPreviousInvoices(@RequestParam("param0") String userId) throws ClassNotFoundException {
        ResponseEntity payment = restTemplate.getForEntity("http://umove-dev.stackroute.io:8094/api/v1/rides/payments/" + userId, Object.class);
        List<Payment> paymentList = new ArrayList<>();
        return new ResponseEntity<>(invoiceService.getPreviousInvoices(paymentList,10), HttpStatus.OK);
    }

}
