package com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.controller.TopRestaurantController;
import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.model.Restaurant;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopRestaurantControllerTest {
    private MockMvc mockMvcTopRestaurantController;

    @MockBean
    private TopRestaurantController topRestaurantController;

    @Mock
    TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private RestaurantService restaurantService;

    @Before
    public void setUp() throws Exception {
        mockMvcTopRestaurantController = MockMvcBuilders.standaloneSetup(topRestaurantController)
                .build();
    }
//    @Test
//    public void getTopRestaurantsTest() throws Exception {
//        Mockito
//                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                .thenReturn(getData());
//        Mockito
//                .when(restaurantService.getTopRestaurants(getData(), ""))
//                .thenReturn("return string");
//        Mockito
//                .when(topRestaurantController.getTopRestaurants("userId"))
//                .thenReturn(getResultPreviousInvoices());
//        assertEquals(new ResponseEntity<>(resultExpectingPreviousInvoices(), HttpStatus.OK), invoiceController.getPreviousInvoices("userId"));
//    }
//    public JSONObject getData() {
//        JSONObject jsonObject = new JSONObject();
//        Restaurant restaurant = new Restaurant();
//        jsonObject.put("return string");
//        return jsonObject;
//
//    }
//    public String getTopRestaurants(JSONObject jsonObject, String city) {
//        return new String("return string");
//    }
//    }




}
