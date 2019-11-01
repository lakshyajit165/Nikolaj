//package com.stackroute.helpdesk.commanddesignframework.commands.invoice.service;
//
//import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Bookings;
//import org.json.simple.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.junit.Assert.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class InvoiceServiceTest {
//
//    @Mock
//    private InvoiceService invoiceService;
//
//    @Mock
//    TestRestTemplate restTemplate = new TestRestTemplate();
//
//    private JSONObject jsonObject = new JSONObject();
//
//    @Before
//    public void setUp() throws Exception {
//        jsonObject.put("data", new ArrayList<>());
//    }
//
//    @Test
//    public void getPreviousInvoicesTest() {
//        Mockito
//                .when(invoiceService.getPreviousInvoices(jsonObject, 10))
//                .thenReturn(resultExpectingPreviousInvoicesTest());
//        assertEquals(10, invoiceService.getPreviousInvoices(jsonObject,10).size());
//    }
//
//    @Test
//    public void getPreviousInvoicesFailTest() {
//        Mockito
//                .when(invoiceService.getPreviousInvoices(jsonObject, 1))
//                .thenReturn(resultExpectingPreviousInvoicesFailTest());
//        assertNotEquals(10, invoiceService.getPreviousInvoices(jsonObject,10).size());
//    }
//
//    public List<String> resultExpectingPreviousInvoicesTest(){
//        List<String> resultList = new ArrayList<>();
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        resultList.add("");
//        return resultList;
//    }
//
//    public List<String> resultExpectingPreviousInvoicesFailTest(){
//        List<String> resultList = new ArrayList<>();
//        resultList.add("");
//        return resultList;
//    }
//}
