package com.stackroute.helpdesk.commanddesignframework.commands.advancedorder;

import com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.model.AdvanceOrder;
import com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.service.AdvanceOrderService;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvanceOrderServiceTest {
	@Mock
	private AdvanceOrderService advanceOrderService;

	@Mock
	TestRestTemplate restTemplate = new TestRestTemplate();

	private JSONObject jsonObject = new JSONObject();
	List<AdvanceOrder> advanceOrderList = new ArrayList<AdvanceOrder>();

	@Before
	public void setUp() throws Exception {
		jsonObject.put("data", new ArrayList<>());
		advanceOrderList.add(new AdvanceOrder());
	}

	@Test
	public void getAdvanceOrderListServiceTest() {
		Mockito
				.when(advanceOrderService.getAdvanceOrderList(jsonObject))
				.thenReturn(new ArrayList<String>());
		assertEquals(0, advanceOrderService.getAdvanceOrderList(jsonObject).size());
	}

	@Test
	public void getAdvanceOrderListServiceFailTest() {
		Mockito
				.when(advanceOrderService.getAdvanceOrderList(jsonObject))
				.thenReturn(new ArrayList<String>());
		assertNotEquals(1, advanceOrderService.getAdvanceOrderList(jsonObject).size());
	}

}
