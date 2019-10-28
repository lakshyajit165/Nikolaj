package com.stackroute.helpdesk.commanddesignframework.commands.offers.service;

import com.stackroute.helpdesk.commanddesignframework.commands.offers.model.Offer;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OffersServiceTest {

    @Mock
    private OfferService offerService;

    @Mock
    TestRestTemplate restTemplate = new TestRestTemplate();

    private JSONObject jsonObject = new JSONObject();
    List<Offer> offerList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        jsonObject.put("data", new ArrayList<>());
        offerList.add(new Offer());
    }

    @Test
    public void getAllOffersServiceTest() {
        Mockito
                .when(offerService.getAllOffers(jsonObject))
                .thenReturn(new ArrayList<String>());
        assertEquals(0, offerService.getAllOffers(jsonObject).size());
    }

    @Test
    public void getAllOffersServiceFailTest() {
        Mockito
                .when(offerService.getAllOffers(jsonObject))
                .thenReturn(new ArrayList<String>());
        assertNotEquals(1, offerService.getAllOffers(jsonObject).size());
    }
}
