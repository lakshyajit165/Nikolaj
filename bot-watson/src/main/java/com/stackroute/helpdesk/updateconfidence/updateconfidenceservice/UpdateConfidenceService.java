package com.stackroute.helpdesk.updateconfidence.updateconfidenceservice;

import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;

import com.stackroute.helpdesk.updateconfidence.model.Feedback;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateConfidenceService {

    @Autowired
    GetConfidence getConfidence;

    @Autowired
    Neo4jService neo4jService;

    public String updateConfidence(Feedback feedback) {
       Integer confidence= getConfidence.getConfidence(feedback.getIntentName(),feedback.getCommandName(),null);

        JSONObject obj = new JSONObject();
        switch (feedback.getRating()){
            case 0:
                confidence=confidence-5;
                break;
            case 1:
                confidence=confidence-3;
                break;
            case 2:
                confidence=confidence-1;
                break;
            case 3:
                confidence=confidence+1;
                break;
            case 4:
                confidence=confidence+3;
                break;
            case 5:
                confidence=confidence+5;
                break;
            default:
                break;
        }
        if(confidence>100){
            confidence=100;
        }
        else if(confidence<0){
           confidence=0;
        }
        obj.put("intentName",feedback.getIntentName());
        obj.put("commandName",feedback.getCommandName());
        obj.put("confidence",confidence);
        neo4jService.updateConfidence(obj);

         return "confidence for intent and command updated sucessfully";
    }
}
