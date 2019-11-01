//package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.service;
//
//import org.json.simple.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class KycStatusServiceTest {
//
//    @Mock
//    private KycStatusService kycStatusService;
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
//    public void getKycStatusServiceTest() {
//        Mockito
//                .when(kycStatusService.getKycStatus(jsonObject))
//                .thenReturn(resultExpecting());
//        assertEquals(1, kycStatusService.getKycStatus(jsonObject).size());
//    }
//
//    @Test
//    public void getKycStatusServiceFailTest() {
//        Mockito
//                .when(kycStatusService.getKycStatus(jsonObject))
//                .thenReturn(resultExpecting());
//        assertNotEquals(10, kycStatusService.getKycStatus(jsonObject).size());
//    }
//
//    public List<String> resultExpecting(){
//        List<String> resultList = new ArrayList<>();
//        resultList.add("");
//        return resultList;
//    }
//
//}
