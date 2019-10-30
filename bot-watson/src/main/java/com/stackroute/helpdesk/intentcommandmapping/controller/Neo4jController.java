package com.stackroute.helpdesk.intentcommandmapping.controller;


import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.Intent;
import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Neo4jController class helps to listen the user requests
 *
 * Gives response
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/optimus/api/v1")
public class Neo4jController {

    HashMap<String,Object> responseObject;

    @Autowired
    Neo4jServiceRepo neo4jServiceRepo;

    /**
     *
     * This method is used to get all the Intents
     * It will make a function call to neo4jService class
     *
     * @return ResponseEntity which contains responseObject and the Http status.
     */
    @GetMapping("/intent")
    public ResponseEntity<HashMap<String,?>> getAllIntents() {
            responseObject = new HashMap<>();
            responseObject.put("result", neo4jServiceRepo.getAllIntents());
            responseObject.put("message", "Successfully displayed the Intents");
            responseObject.put("error", "No error");
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    /**
     *
     * This method is used to create a intent
     * It will make a function call to neo4jService class  with map as a parameter
     *
     * @return ResponseEntity which contains responseObject and the Http status.
     */
    @PostMapping("/intent")
    public ResponseEntity<HashMap<String,?>> addIntent(@RequestBody Intent intent){


        responseObject = new HashMap<>();
        responseObject.put("result",neo4jServiceRepo.addIntent(intent));
        responseObject.put("message","Successfully displayed the inserted intent");
        responseObject.put("error","No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    /**
     *
     * This method is used to update a intent status
     * It will make a function call to neo4jService class with map as a parameter
     *
     * @return ResponseEntity which contains responseObject and the Http status.
     */
    @PatchMapping("/intentStatus")
    public ResponseEntity<HashMap<String,?>> updateIntentStatus(@RequestBody Intent intent){
        responseObject = new HashMap<>();
        responseObject.put("result",neo4jServiceRepo.updateIntentStatus(intent));
        responseObject.put("message","Successfully displayed the updated intent");
        responseObject.put("error","No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/intentName/command")
    public ResponseEntity<HashMap<String,?>> addCommand(@RequestBody Map map) {
        responseObject = new HashMap<>();
        responseObject.put("result",neo4jServiceRepo.addCommand(map));
        responseObject.put("message","Successfully displayed the inserted commanded");
        responseObject.put("error","No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    /**
     *
     * This method is used to update a command parameter
     * It will make a function call to neo4jService class with map as a parameter
     *
     * @return ResponseEntity which contains responseObject and the Http status.
     */
    @PatchMapping("/command")
    public ResponseEntity<HashMap<String,?>> updateCommandParameter(@RequestBody Command command){
        responseObject = new HashMap<>();
        responseObject.put("result",neo4jServiceRepo.updateCommandParameter(command));
        responseObject.put("message","Successfully displayed the updated command");
        responseObject.put("error","No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/intent/command")
    public ResponseEntity<HashMap<String,?>> getAll(){

        responseObject = new HashMap<>();
        System.out.println("controller1");
        responseObject.put("result", neo4jServiceRepo.getAll());
        responseObject.put("message","Successfully displayed the intents,commands and relationship");
        responseObject.put("error","No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    @PostMapping("/intent/command")
    public ResponseEntity<HashMap<String,?>> addIntentAndCommand(@RequestBody Map map){
        responseObject = new HashMap<>();
        responseObject.put("result",neo4jServiceRepo.addIntentAndCommand(map));
        responseObject.put("message","Successfully displayed the inserted relationship");
        responseObject.put("error","No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    @PatchMapping("/intentName/confidence/commandName")
    public ResponseEntity<HashMap<String,?>> updateConfidence(@RequestBody Map map){
        responseObject = new HashMap<>();
        responseObject.put("result",neo4jServiceRepo.updateConfidence(map));
        responseObject.put("message","Successfully displayed the updated relationship");
        responseObject.put("error","No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/intent/intentName")
    public ResponseEntity<HashMap<String,?>> getCommandByName(@RequestParam(value = "intentName") String intentName
                                                            ,@RequestParam(value = "relationshipName", required = false) String relationshipName){
        responseObject = new HashMap<>();
        responseObject.put("result", neo4jServiceRepo.getCommandByName(intentName,relationshipName));
        responseObject.put("message", "Successfully displayed the command");
        responseObject.put("error", "No error");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }



}
