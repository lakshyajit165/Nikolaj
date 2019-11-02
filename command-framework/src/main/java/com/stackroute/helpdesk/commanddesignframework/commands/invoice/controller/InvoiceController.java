package com.stackroute.helpdesk.commanddesignframework.commands.invoice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Payment;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.service.InvoiceService;
import com.stackroute.helpdesk.commanddesignframework.commands.offers.model.Campaign;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
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
    public ResponseEntity<Object> getLastInvoice(@RequestParam("param0") String userId) throws Exception {
//        ResponseEntity<Object> invoices = restTemplate.getForEntity("http://umove-dev.stackroute.io:8094/api/v1/rides/payments/" + userId, Object.class);
//        List<Payment> campaignList = (List<Payment>) (((LinkedHashMap)invoices.getBody()).get("data"));
        ArrayList<String> resultList = new ArrayList<>();
//        campaignList.forEach(campaign -> {
//            System.out.println("campaign = " + campaign);
            String result = "invoice has been sent in the mail";
            resultList.add(result);
//        });
        invoiceService.getPreviousInvoices();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

//    @GetMapping("/previousinvoices")
//    public ResponseEntity<Object> getPreviousInvoices(@RequestParam("param0") String userId) throws ClassNotFoundException {
//        List<Payment> paymentList = new ArrayList<>();
//        HttpEntity<List<Payment>> request = new HttpEntity<>(paymentList);
//        ResponseEntity<LinkedHashMap> payment = restTemplate.getForEntity("http://umove-dev.stackroute.io:8094/api/v1/rides/payments/" + userId, LinkedHashMap.class);
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println("data = " + payment.getBody().get("data"));
//        System.out.println("values = " + payment.getBody().values());
//        System.out.println("dataclass = " + payment.getBody().get("data").getClass());
//        List<Payment> paymentList1 = mapper.convertValue(payment.getBody().values(), new TypeReference<List<Payment>>(){});
////        return new ResponseEntity<>(invoiceService.getPreviousInvoices(paymentList1,10), HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
