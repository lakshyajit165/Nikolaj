package com.stackroute.helpdesk.userRequest.controller;

import com.stackroute.helpdesk.userRequest.model.ChatMessage;
import com.stackroute.helpdesk.userRequest.model.SuggestionsModel;
import com.stackroute.helpdesk.userRequest.service.ChatServiceInterface;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/optimus/api/v1")
public class SuggestionsController {

    HashMap<String,Object> responseObject;

    @Autowired
    private ChatServiceInterface chatServiceInterface;

    @GetMapping("/suggestions")
    public ResponseEntity<HashMap<String,?>> getSuggestions(@RequestParam String id){
//        SuggestionsModel suggestionsModel=new SuggestionsModel("1","abc");
        responseObject = new HashMap<>();
//        responseObject.put("result", suggestionsModel);
        responseObject.put("result", chatServiceInterface.getSuggestions(id));
        responseObject.put("message", "Successfully given suggestions");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/rating")
    public ResponseEntity<HashMap<String,?>> getRating(@RequestParam Integer rating){
//        SuggestionsModel suggestionsModel=new SuggestionsModel("1","abc");
        responseObject = new HashMap<>();
//        responseObject.put("result", suggestionsModel);
        responseObject.put("result", chatServiceInterface.updateConfidence(rating));
        responseObject.put("message", "Successfully given suggestions");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/simulation")
    public ResponseEntity<HashMap<String,?>> getSimulationResponse(@RequestParam String query){
        responseObject = new HashMap<>();
        ChatMessage simulationMessage = new ChatMessage();
        simulationMessage.setContent(query);
        responseObject.put("result", chatServiceInterface.postQuery(simulationMessage));
        responseObject.put("message", "Successfully given suggestions");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
//    @GetMapping("/suggestions")
//    public ResponseEntity<HashMap<String,?>> getSuggestions(@RequestParam String id){
//        responseObject = new HashMap<>();
//        responseObject.put("result", chatServiceInterface.getSuggestion(id));
//        responseObject.put("message", "Successfully displayed ticket");
//        responseObject.put("error", "No error");
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }

//    @PatchMapping("/ticket")
//    public ResponseEntity<HashMap<String,?>> updateTicket(@RequestBody Map map){
//        responseObject = new HashMap<>();
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//
//    }
}
