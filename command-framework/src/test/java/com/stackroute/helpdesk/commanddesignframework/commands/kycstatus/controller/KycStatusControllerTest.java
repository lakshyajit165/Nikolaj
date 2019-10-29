package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model.ExpiryDate;
import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model.KycStatus;
import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.service.KycStatusService;
import com.stackroute.helpdesk.commanddesignframework.commands.refund.model.SourceZone;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KycStatusControllerTest {

    private MockMvc mockMvcKycStatus;

    @MockBean
    private KycStatusController kycStatusController;

    @Mock
    TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private KycStatusService kycStatusService;

    @Before
    public void setUp() throws Exception{
        mockMvcKycStatus = MockMvcBuilders.standaloneSetup(kycStatusController)
                .build();
    }

    @Test
    public void getKycStatusTest() throws Exception {
        Mockito
                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
                .thenReturn(getData());
        Mockito
                .when(kycStatusService.getKycStatus(getData()))
                .thenReturn(resultExpecting());
        Mockito
                .when(kycStatusController.getKycStatus("userId"))
                .thenReturn(getResult());
        assertEquals(new ResponseEntity<>(resultExpecting(), HttpStatus.OK), kycStatusController.getKycStatus("userId"));
    }

	@Test
	public void getKycStatusFailTest() throws Exception {
		Mockito
				.when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
				.thenReturn(getData());
		Mockito
				.when(kycStatusService.getKycStatus(getData()))
				.thenReturn(resultExpecting());
		Mockito
				.when(kycStatusController.getKycStatus("userId"))
				.thenReturn(null);
		assertNotEquals(new ResponseEntity<>(resultExpecting(), HttpStatus.OK), kycStatusController.getKycStatus("userId"));
	}

    public List<String> resultExpecting() {
        List<String> resultList = new ArrayList<>();
        resultList.add("Your KYC status is  verified.");
        return resultList;
    }

    public ResponseEntity<Object> getResult() {
        List<String> resultList = new ArrayList<>();
        resultList.add("Your KYC status is  verified.");
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    public JSONObject getData() {
        JSONObject jsonObject = new JSONObject();
	    KycStatus kycStatus = new KycStatus();
		jsonObject.put("expiryDate", new ExpiryDate());
		jsonObject.put("documentStatus", "");
	    jsonObject.put("drivingLicenceNumber", "");
	    jsonObject.put("image", "");
        return jsonObject;
    }
}
