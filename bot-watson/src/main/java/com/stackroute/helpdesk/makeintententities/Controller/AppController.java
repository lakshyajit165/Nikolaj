package com.stackroute.helpdesk.makeintententities.Controller;

import com.stackroute.helpdesk.makeintententities.Models.Entiti;
import com.stackroute.helpdesk.makeintententities.Models.Intent;
import com.stackroute.helpdesk.makeintententities.Services.EntityService;
import com.stackroute.helpdesk.makeintententities.Services.IntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/optimus/api/v1/intentandentitycreator")
public class AppController {
    @Autowired
    IntentService intentService;
    @Autowired
    EntityService entityService;
    HashMap<String,Object> responseObject;



    @PostMapping("/intent")
    public ResponseEntity<HashMap<String,Object>> createIntent(@RequestBody Intent intent){
        String intentName= intentService.createIntent(intent.getName(),intent.getTestStrings());
        responseObject=new HashMap<>();
        responseObject.put("result",intentName);
        responseObject.put("errors",false);
        responseObject.put("message","Ok");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }



    @PostMapping("/entity")
    public ResponseEntity<HashMap<String,Object>> entity(@RequestBody Entiti entity){
        String entityName=  entityService.createEntity(entity);
        responseObject=new HashMap<>();
        responseObject.put("result",entityName);
        responseObject.put("errors",false);
        responseObject.put("message","Ok");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }

    @DeleteMapping("/intent")
    public void deleteAllIntent(){
        intentService.deleteIntent();
    }
}
