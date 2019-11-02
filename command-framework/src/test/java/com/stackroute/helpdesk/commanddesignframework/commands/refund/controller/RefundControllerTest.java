//package com.stackroute.helpdesk.commanddesignframework.commands.refund.controller;
//
//
//import org.json.simple.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RefundControllerTest {
//
//    private MockMvc mockMvcRefundController;
//
//    @MockBean
//    private RefundController refundController;
//
//    @Mock
//    TestRestTemplate restTemplate = new TestRestTemplate();
//
//    private JSONObject jsonObject = new JSONObject();
//
//    @Before
//    public void setUp() throws Exception{
//        mockMvcRefundController = MockMvcBuilders.standaloneSetup(refundController)
//                .build();
//        jsonObject.put("status","OK");
//    }
//
//    @Test
//    public void initiateRefundTest() throws Exception {
//        Mockito
//                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                .thenReturn(getData());
//        Mockito
//                .when(refundController.initiateRefund("" ,1, 2))
//                .thenReturn(new ResponseEntity<>(expectingResult(), HttpStatus.OK));
//        assertEquals(new ResponseEntity<>(expectingResult(), HttpStatus.OK), refundController.initiateRefund("", 1, 2));
//    }
//
//    @Test
//    public void initiateRefundFailTest() throws Exception {
//        Mockito
//                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                .thenReturn(getData());
//        Mockito
//                .when(refundController.initiateRefund("" ,1, 2))
//                .thenReturn(new ResponseEntity<>(resultSent(), HttpStatus.OK));
//        assertNotEquals(new ResponseEntity<>(expectingResult(), HttpStatus.OK), refundController.initiateRefund("", 1, 2));
//    }
//
//    @Test
//    public void initiateRefundFailTestException() throws Exception {
//        Mockito
//                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                .thenReturn(getData());
//        mockMvcRefundController.perform(MockMvcRequestBuilders.get("/refund"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }
//
//    public JSONObject getData() {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("_id", "5da73a668aed1e9366a7af97");
//        jsonObject.put("name", "diwali offer");
//        jsonObject.put("objective", "increase target customer base");
//        jsonObject.put("startDate", "");
//        jsonObject.put("endDate", "");
//        jsonObject.put("promocode", "AUEN67");
//        jsonObject.put("discountPercent", 50);
//        return jsonObject;
//    }
//
//    public List<String> getResult(){
//        List<String> result = new ArrayList<>();
//        result.add("Get 25% on SBI credit card from 1-10-2019 to 16-10-2019");
//        return result;
//    }
//
//    public List<String> resultSent(){
//        List<String> result = new ArrayList<>();
//        result.add("Get 25% on SBI credit card from 1-10-2019 to 16-10-2019");
//        result.add("Get 25% on SBI credit card from 1-10-2019 to 16-10-2019");
//        return result;
//    }
//
//    public List<String> expectingResult(){
//        List<String> result = new ArrayList<>();
//        result.add("Get 25% on SBI credit card from 1-10-2019 to 16-10-2019");
//        return result;
//    }
//
//}
