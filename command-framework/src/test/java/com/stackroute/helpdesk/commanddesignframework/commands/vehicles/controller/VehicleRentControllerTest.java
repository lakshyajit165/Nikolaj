//package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.controller;
//
//import com.stackroute.helpdesk.commanddesignframework.commands.vehicles.services.VehicleRentService;
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
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class VehicleRentControllerTest {
//
//    private MockMvc mockMvcVehicleRentController;
//
//    @MockBean
//    private VehicleRentController vehicleRentController;
//
//    @Mock
//    TestRestTemplate restTemplate = new TestRestTemplate();
//
//    @MockBean
//    private VehicleRentService vehicleRentService;
//
//    private JSONObject jsonObject = new JSONObject();
//
//    @Before
//    public void setUp() throws Exception{
//        mockMvcVehicleRentController = MockMvcBuilders.standaloneSetup(vehicleRentController)
//                .build();
//        jsonObject.put("status","OK");
//    }
//
//    @Test
//    public void getAllTypeVehicleRentTest() throws Exception {
//         Mockito
//                 .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                 .thenReturn(getData());
//         Mockito
//                 .when(vehicleRentService.getAllTypeVehicleRent(getData()))
//                 .thenReturn(resultExpectingAllVehicleRent());
//         Mockito
//                 .when(vehicleRentController.getAllVehicleTypeRent())
//                 .thenReturn(getResultAllVehicleRent());
//         assertEquals(new ResponseEntity<>(resultExpectingAllVehicleRent(), HttpStatus.OK), vehicleRentController.getAllVehicleTypeRent());
//     }
//
//    @Test
//    public void getAllTypeVehicleRentFailTest() throws Exception {
//        Mockito
//                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                .thenReturn(getData());
//        Mockito
//                .when(vehicleRentService.getAllTypeVehicleRent(getData()))
//                .thenReturn(resultExpectingAllVehicleRent());
//        Mockito
//                .when(vehicleRentController.getAllVehicleTypeRent())
//                .thenReturn(getResultAllVehicleRent());
//        assertNotEquals(new ResponseEntity<>(resultExpectingParticularVehicleRent(), HttpStatus.OK), vehicleRentController.getAllVehicleTypeRent());
//    }
//
//    @Test
//    public void getParticularTypeVehicleRentTest() throws Exception {
//        Mockito
//                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                .thenReturn(getData());
//        Mockito
//                .when(vehicleRentService.getParticularTypeVehicleRent(getData(), "Activa"))
//                .thenReturn(resultExpectingParticularVehicleRent());
//        Mockito
//                .when(vehicleRentController.getParticularTypeVehiclesRent("Activa"))
//                .thenReturn(getResultAllVehicleRent());
//        assertNotEquals(new ResponseEntity<>(resultExpectingParticularVehicleRent(), HttpStatus.OK), vehicleRentController.getParticularTypeVehiclesRent("Activa"));
//    }
//
//    @Test
//    public void getParticularTypeVehicleRentFailTest() throws Exception {
//        Mockito
//                .when(restTemplate.getForObject("http://localhost:3000/result", JSONObject.class))
//                .thenReturn(getData());
//        Mockito
//                .when(vehicleRentService.getParticularTypeVehicleRent(getData(), "Activa"))
//                .thenReturn(resultExpectingParticularVehicleRent());
//        Mockito
//                .when(vehicleRentController.getParticularTypeVehiclesRent("Activa"))
//                .thenReturn(getResultParticularVehicleRent());
//        assertNotEquals(new ResponseEntity<>(resultExpectingAllVehicleRent(), HttpStatus.OK), vehicleRentController.getParticularTypeVehiclesRent("Activa"));
//    }
//
//    public ResponseEntity<Object> getResultParticularVehicleRent() {
//        List<String> resultList = new ArrayList<>();
//        resultList.add("Activa's cost per km = 3 and cost per minute = 3");
//        return new ResponseEntity<>(resultList, HttpStatus.OK);
//    }
//    public List<String> resultExpectingParticularVehicleRent() {
//        List<String> resultList = new ArrayList<>();
//        resultList.add("Activa's cost per km = 3 and cost per minute = 3");
//        return resultList;
//    }
//    public ResponseEntity<Object> getResultAllVehicleRent() {
//        List<String> resultList = new ArrayList<>();
//        resultList.add("Activa's cost per km = 3 and cost per minute = 3");
//        resultList.add("KTM's cost per km = 2 and cost per minute = 6");
//        return new ResponseEntity<>(resultList, HttpStatus.OK);
//    }
//    public List<String> resultExpectingAllVehicleRent() {
//        List<String> resultList = new ArrayList<>();
//        resultList.add("Activa's cost per km = 3 and cost per minute = 3");
//        resultList.add("KTM's cost per km = 2 and cost per minute = 6");
//        return resultList;
//    }
//    public JSONObject getData() {
//        JSONObject jsonObject = new JSONObject();
//        Type type = new Type();
//        jsonObject.put("id", "5da73a668aed1e9366a7af97");
//        jsonObject.put("zoneid", "autocancelled");
//        jsonObject.put("registrationNo", "rider");
//        jsonObject.put("insurance_no", "sourceZone");
//        jsonObject.put("status", "destinationZones");
//        jsonObject.put("type", type);
//        jsonObject.put("time", new Date());
//        jsonObject.put("lastServiceDate", new Date());
//        jsonObject.put("vehiclePurchased", new Date());
//        return jsonObject;
//    }
//}
