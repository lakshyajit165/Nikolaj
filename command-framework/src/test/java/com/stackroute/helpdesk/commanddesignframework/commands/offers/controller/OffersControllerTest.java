package com.stackroute.helpdesk.commanddesignframework.commands.offers.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.offers.service.OfferService;
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
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OffersControllerTest {

    private MockMvc mockMvcOfferController;

    @MockBean
    private OfferController offerController;

    @Mock
    TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private OfferService offerService;

    @Before
    public void setUp() throws Exception{
        mockMvcOfferController = MockMvcBuilders.standaloneSetup(offerController)
                .build();
    }

    @Test
    public void getAllOffersTest() throws Exception {
        Mockito
                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
                .thenReturn(getData());
        Mockito
                .when(offerService.getAllOffers(getData()))
                .thenReturn(getResult());
        Mockito
                .when(offerController.getAllOffers("userId"))
                .thenReturn(new ResponseEntity<>(getResult(), HttpStatus.OK));
        assertEquals(new ResponseEntity<>(expectingResult(), HttpStatus.OK), offerController.getAllOffers("userId"));
    }

    @Test
    public void getAllOffersFailTest() throws Exception {
        Mockito
                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
                .thenReturn(getData());
        Mockito
                .when(offerService.getAllOffers(getData()))
                .thenReturn(getResult());
        Mockito
                .when(offerController.getAllOffers("userId"))
                .thenReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));
        assertNotEquals(new ResponseEntity<>(expectingResult(), HttpStatus.OK), offerController.getAllOffers("userId"));
    }

    public JSONObject getData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("_id", "5da73a668aed1e9366a7af97");
        jsonObject.put("name", "diwali offer");
        jsonObject.put("objective", "increase target customer base");
        jsonObject.put("startDate", "");
        jsonObject.put("endDate", "");
        jsonObject.put("promocode", "AUEN67");
        jsonObject.put("discountPercent", 50);
        return jsonObject;
    }

    public List<String> getResult(){
        List<String> result = new ArrayList<>();
        result.add("Get 25% on SBI credit card from 1-10-2019 to 16-10-2019");
        return result;
    }

    public List<String> expectingResult(){
        List<String> result = new ArrayList<>();
        result.add("Get 25% on SBI credit card from 1-10-2019 to 16-10-2019");
        return result;
    }
}
