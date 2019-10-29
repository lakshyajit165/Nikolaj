package com.stackroute.helpdesk.commanddesignframework.commands.takefeedback.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model.ExpiryDate;
import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model.KycStatus;
import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.controller.TopRestaurantController;
import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.services.RestaurantService;
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
public class FeedbackControllerTest {
	private MockMvc mockMvcTakeFeedBackController;

	@MockBean
	private FeedbackController feedbackController;

	@Mock
	TestRestTemplate restTemplate = new TestRestTemplate();

	@Before
	public void setUp() throws Exception {
		mockMvcTakeFeedBackController = MockMvcBuilders.standaloneSetup(feedbackController)
				.build();
	}
	@Test
	public void takeFeedbackTest() throws Exception {
		Mockito
				.when(feedbackController.takeFeedback("userId", "feedback"))
				.thenReturn(getResult());
		assertEquals(new ResponseEntity<>(resultExpecting(), HttpStatus.OK), feedbackController.takeFeedback("userId", "feedback"));
	}

	@Test
	public void takeFeedbackFailTest() throws Exception {
		Mockito
				.when(feedbackController.takeFeedback("userId", "feedback"))
				.thenReturn(getResultFailTest());
		assertNotEquals(new ResponseEntity<>(resultExpecting(), HttpStatus.OK), feedbackController.takeFeedback("userId", "feedback"));
	}


	public List<String> resultExpecting() {
		List<String> resultList = new ArrayList<>();
		resultList.add("feedback has been received for orderId:");
		return resultList;
	}

	public ResponseEntity<Object> getResult() {
		List<String> resultList = new ArrayList<>();
		resultList.add("feedback has been received for orderId:");
		return new ResponseEntity<>(resultList, HttpStatus.OK);
	}

	public ResponseEntity<Object> getResultFailTest() {
		List<String> resultList = new ArrayList<>();
		resultList.add("order Id was not found");
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
