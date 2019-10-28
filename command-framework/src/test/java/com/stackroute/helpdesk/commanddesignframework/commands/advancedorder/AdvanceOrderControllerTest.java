package com.stackroute.helpdesk.commanddesignframework.commands.advancedorder;

import com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.controller.AdvanceOrderController;
import com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.service.AdvanceOrderService;
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
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvanceOrderControllerTest {
	private MockMvc mockMvcOfferController;
	@MockBean
	private AdvanceOrderController advanceOrderController;
	@Mock
	TestRestTemplate restTemplate = new TestRestTemplate();

	@MockBean
	private AdvanceOrderService advanceOrderService;

	@Before
	public void setUp() throws Exception{
		mockMvcOfferController = MockMvcBuilders.standaloneSetup(advanceOrderController)
				.build();
	}

	@Test
	public void advanceOrderTest() throws Exception {
		Mockito
				.when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
				.thenReturn(getData());
		Mockito
				.when(advanceOrderService.getAdvanceOrderList(getData()))
				.thenReturn(getResult());
		Mockito
				.when(advanceOrderController.advanceOrder())
				.thenReturn(new ResponseEntity<>(getResult(), HttpStatus.OK));
		assertEquals(new ResponseEntity<>(expectingResult(), HttpStatus.OK), advanceOrderController.advanceOrder());
	}
	@Test
	public void advcanceOrderFailTest() throws Exception {
		Mockito
				.when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
				.thenReturn(getData());
		Mockito
				.when(advanceOrderService.getAdvanceOrderList(getData()))
				.thenReturn(getResult());
		Mockito
				.when(advanceOrderController.advanceOrder())
				.thenReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));
		assertNotEquals(new ResponseEntity<>(expectingResult(), HttpStatus.OK), advanceOrderController.advanceOrder());
	}

	public JSONObject getData() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("order_id", "5da73a668aed1e9366a7af97");
		jsonObject.put("name", "new item 1");
		return jsonObject;
	}
	public List<String> getResult(){
		List<String> result = new ArrayList<>();
		result.add("advance order on order id 103 is new item 1");
		return result;
	}
	public List<String> expectingResult(){
		List<String> result = new ArrayList<>();
		result.add("advance order on order id 103 is new item 1");
		return result;
	}
}