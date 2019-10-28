package com.stackroute.helpdesk.commanddesignframework.commands.invoice.controller;


import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Bookings;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.PaymentMethod;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Rider;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Vehicle;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.service.InvoiceService;
import com.stackroute.helpdesk.commanddesignframework.commands.refund.model.SourceZone;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceControllerTest {

    private MockMvc mockMvcInvoiceController;

    @MockBean
    private InvoiceController invoiceController;

    @Mock
    TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private InvoiceService invoiceService;

    @Before
    public void setUp() throws Exception {
        mockMvcInvoiceController = MockMvcBuilders.standaloneSetup(invoiceController)
                .build();
    }

    @Test
    public void getPreviousInvoicesTest() throws Exception {
        Mockito
                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
                .thenReturn(getData());
        Mockito
                .when(invoiceService.getPreviousInvoices(getData(), 10))
                .thenReturn(resultExpectingPreviousInvoices());
        Mockito
                .when(invoiceController.getPreviousInvoices("userId"))
                .thenReturn(getResultPreviousInvoices());
        assertEquals(new ResponseEntity<>(resultExpectingPreviousInvoices(), HttpStatus.OK), invoiceController.getPreviousInvoices("userId"));
    }

    @Test
    public void getLastInvoiceTest() throws Exception {
        Mockito
                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
                .thenReturn(getData());
        Mockito
                .when(invoiceService.getPreviousInvoices(getData(), 1))
                .thenReturn(resultExpectingLastInvoice());
        Mockito
                .when(invoiceController.getPreviousInvoices("userId"))
                .thenReturn(getResultLastInvoice());
        assertEquals(new ResponseEntity<>(resultExpectingLastInvoice(), HttpStatus.OK), invoiceController.getPreviousInvoices("userId"));
    }

    @Test
    public void getLastInvoiceFailTest() throws Exception {
        Mockito
                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
                .thenReturn(getData());
        Mockito
                .when(invoiceService.getPreviousInvoices(getData(), 1))
                .thenReturn(resultExpectingLastInvoice());
        Mockito
                .when(invoiceController.getLastInvoice("userId", "bookingStatus"))
                .thenReturn(getResultLastInvoice());
        assertNotEquals(new ResponseEntity<>(resultExpectingPreviousInvoices(), HttpStatus.OK), invoiceController.getLastInvoice("userId", "bookingStatus"));
    }

    @Test
    public void getPreviousInvoicesFailTest() throws Exception {
        Mockito
                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
                .thenReturn(getData());
        Mockito
                .when(invoiceService.getPreviousInvoices(getData(), 10))
                .thenReturn(resultExpectingPreviousInvoices());
        Mockito
                .when(invoiceController.getPreviousInvoices("userId"))
                .thenReturn(getResultPreviousInvoices());
        assertNotEquals(new ResponseEntity<>(resultExpectingLastInvoice(), HttpStatus.OK), invoiceController.getPreviousInvoices("userId"));
    }

    public ResponseEntity<Object> getResultLastInvoice() {
        List<String> resultList = new ArrayList<>();
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
    public List<String> resultExpectingPreviousInvoices() {
        List<String> resultList = new ArrayList<>();
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        return resultList;
    }
    public ResponseEntity<Object> getResultPreviousInvoices() {
        List<String> resultList = new ArrayList<>();
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
    public List<String> resultExpectingLastInvoice() {
        List<String> resultList = new ArrayList<>();
        resultList.add("The ride was booked on 2015-10-10T05:59:47 -06:-30 with a Activa for 498km.\nThe total cost for the ride was 954 which was paid through UPI.\nThe ride continued from 2014-03-18T09:15:35 -06:-30 to 2015-09-09T02:21:04 -06:-30");
        return resultList;
    }
    public JSONObject getData() {
        JSONObject jsonObject = new JSONObject();
        Bookings bookings = new Bookings();
        Rider rider = new Rider();
        SourceZone sourceZone = new SourceZone();
        String[] destinationZones = new String[0];
        Vehicle vehicle = new Vehicle();
        PaymentMethod paymentMethod = new PaymentMethod();
        jsonObject.put("_id", "5da73a668aed1e9366a7af97");
        jsonObject.put("status", "autocancelled");
        jsonObject.put("rider", rider);
        jsonObject.put("sourceZone", sourceZone);
        jsonObject.put("destinationZones", destinationZones);
        jsonObject.put("vehicle", vehicle);
        jsonObject.put("paymentMethod", paymentMethod);
        jsonObject.put("bookedAt", "");
        jsonObject.put("rideStartAt", "");
        jsonObject.put("rideEndAt", "");
        jsonObject.put("distance", "");
        jsonObject.put("duration", "");
        jsonObject.put("payment", "");
        return jsonObject;
    }
}