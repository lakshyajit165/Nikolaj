//package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.controller;
//
//import com.stackroute.helpdesk.commanddesignframework.commands.offers.model.Campaign;
//import com.stackroute.helpdesk.commanddesignframework.commands.vehicles.model.Vehicle;
//import com.stackroute.helpdesk.commanddesignframework.commands.vehicles.services.VehicleRentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//
//@RestController
//public class VehicleRentController {
//
////    @Autowired
////    private VehicleRentService vehicleRentService;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @GetMapping("/particulartypevehiclerent")
//    public ResponseEntity<Object> getParticularTypeVehiclesRent(@RequestParam("param0") String typeOfVehicle){
//        ResponseEntity<Vehicle> jsonObject = restTemplate.getForEntity("http://umove-dev.stackroute.io:8095/api/v1/types?name="+typeOfVehicle, Vehicle.class);
//        System.out.println(jsonObject.getBody());
//        String result = jsonObject.getBody().getVehicleType().getName() + "'s cost per km = " + jsonObject.getBody().getVehicleType().getCostPerKm() + " and cost per minute = " + jsonObject.getBody().getVehicleType().getCostPerMin();
//        ArrayList<String> resultList = new ArrayList<>();
//        resultList.add(result);
//        return new ResponseEntity<>(resultList, HttpStatus.OK);
//    }
//
//    @GetMapping("/alltypevehiclerent")
//    public ResponseEntity<Object> getAllVehicleTypeRent(){
//        ResponseEntity<Vehicle> jsonObject = restTemplate.getForEntity("http://umove-dev.stackroute.io:8095/api/v1/types", Vehicle.class);
//        ArrayList<String> resultList = new ArrayList<>();
//            System.out.println(jsonObject.getBody());
//        String result = jsonObject.getBody().getVehicleType().getName() + "'s cost per km = " + jsonObject.getBody().getVehicleType().getCostPerKm() + " and cost per minute = " + jsonObject.getBody().getVehicleType().getCostPerMin();
//        resultList.add(result);
//        return new ResponseEntity<>(resultList, HttpStatus.OK);
//    }
//}
