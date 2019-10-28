package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.vehicles.model.Type;
import com.stackroute.helpdesk.commanddesignframework.commands.vehicles.services.VehicleRentService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class VehicleRentController {

    @Autowired
    private VehicleRentService vehicleRentService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/particulartypevehiclerent")
    public ResponseEntity<Object> getParticularTypeVehiclesRent(@RequestParam("param0") String typeOfVehicle){
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        return new ResponseEntity<>(vehicleRentService.getParticularTypeVehicleRent(jsonObject, typeOfVehicle), HttpStatus.OK);
    }

    @GetMapping("/alltypevehiclerent")
    public ResponseEntity<Object> getAllVehicleTypeRent(){
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        return new ResponseEntity<>(vehicleRentService.getAllTypeVehicleRent(jsonObject), HttpStatus.OK);
    }
}
