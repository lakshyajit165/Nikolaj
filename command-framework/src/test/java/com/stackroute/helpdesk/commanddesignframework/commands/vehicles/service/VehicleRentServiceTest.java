//package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.service;
//
//import com.stackroute.helpdesk.commanddesignframework.commands.vehicles.services.VehicleRentService;
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
//public class VehicleRentServiceTest {
//
//    @Mock
//    private VehicleRentService vehicleRentService;
//
//    @Mock
//    TestRestTemplate restTemplate = new TestRestTemplate();
//
//    private JSONObject jsonObject = new JSONObject();
//
//    @Before
//    public void setUp() throws Exception {
//	    jsonObject.put("data", new ArrayList<>());
//    }
//
//    @Test
//    public void getAllTypeVehicleRentTest() {
//        Mockito
//                .when(vehicleRentService.getAllTypeVehicleRent(jsonObject))
//                .thenReturn(resultExpectingAllVehicleRent());
//        assertEquals(resultExpectingAllVehicleRent(), vehicleRentService.getAllTypeVehicleRent(jsonObject));
//    }
//
//	@Test
//	public void getParticularTypeVehicleRentTest() {
//		Mockito
//				.when(vehicleRentService.getParticularTypeVehicleRent(jsonObject, "KTM"))
//				.thenReturn(resultExpectingParticularVehicleRent());
//		assertEquals(resultExpectingParticularVehicleRent(), vehicleRentService.getParticularTypeVehicleRent(jsonObject, "KTM"));
//	}
//
//	@Test
//	public void getAllTypeVehicleRentFailTest() {
//		Mockito
//				.when(vehicleRentService.getAllTypeVehicleRent(jsonObject))
//				.thenReturn(resultExpectingAllVehicleRent());
//		assertNotEquals(resultExpectingParticularVehicleRent(), vehicleRentService.getAllTypeVehicleRent(jsonObject));
//	}
//
//	@Test
//	public void getParticularTypeVehicleRentFailTest() {
//		Mockito
//				.when(vehicleRentService.getParticularTypeVehicleRent(jsonObject, "KTM"))
//				.thenReturn(resultExpectingParticularVehicleRent());
//		assertNotEquals(resultExpectingAllVehicleRent(), vehicleRentService.getParticularTypeVehicleRent(jsonObject, "KTM"));
//	}
//
//	public List<String> resultExpectingParticularVehicleRent() {
//		List<String> resultList = new ArrayList<>();
//		resultList.add("Activa's cost per km = 3 and cost per minute = 3");
//		return resultList;
//	}
//	public List<String> resultExpectingAllVehicleRent() {
//		List<String> resultList = new ArrayList<>();
//		resultList.add("Activa's cost per km = 3 and cost per minute = 3");
//		resultList.add("KTM's cost per km = 2 and cost per minute = 6");
//		return resultList;
//	}
//}
