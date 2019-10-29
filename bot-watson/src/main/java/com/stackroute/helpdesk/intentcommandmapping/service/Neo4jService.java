package com.stackroute.helpdesk.intentcommandmapping.service;


import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.DataAlreadyExists;
import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.ParameterException;
import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.Intent;
import com.stackroute.helpdesk.intentcommandmapping.model.IntentStatus;
import com.stackroute.helpdesk.intentcommandmapping.repository.Neo4jRepo;
import org.json.simple.JSONObject;
import org.neo4j.driver.v1.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Neo4jService implements Neo4jServiceRepo {

    @Autowired
    Neo4jRepo neo4jRepo;

    public List getAllIntents() {
        return neo4jRepo.getAllIntents();
    }

    public List addIntent(Intent intent) {
        if(!(intent.getIntentName()==null || intent.getIntentStatus()==null)) {
                return neo4jRepo.addIntent(intent.getIntentName(),intent.getIntentStatus());
        }
        throw new ParameterException("Enter all the required parameters");
    }

    public List updateIntentStatus(Intent intent) {
        if(!(intent.getIntentName()==null || intent.getIntentStatus()==null)){
            try {
                return neo4jRepo.updateIntentStatus(intent.getIntentName(),intent.getIntentStatus());
            }
            catch(DatabaseException d){
                throw new DataAlreadyExists("Data already exists");
            }
        }
        throw new ParameterException("Enter all the required parameters");
    }


    public List updateCommandParameter(Command command) {
        if(!(command.getCommandName()==null || command.getCommandParameter()==null)){
            try {
                return neo4jRepo.updateCommandParameter(command.getCommandName(),command.getCommandParameter());
            }
            catch(DatabaseException d){
                throw new DataAlreadyExists("Data already exists");
            }
        }
        throw new ParameterException("Enter all the required parameters");
    }

    public List addCommand(Map map) {

        if(map.size()==4){
            return neo4jRepo.addCommand((String) map.get("commandName"),(List) map.get("commandParameter"),(List) (map.get("intentName")),50,(String) (map.get("relationshipName")));
        }
        else if(map.size()==3){
            return neo4jRepo.addCommand((String) map.get("commandName"),(List) map.get("commandParameter"),(List) (map.get("intentName")),50);
        }
        throw new ParameterException("Enter all the required parameters");
    }

    public List getConfidence(String intentName, String commandName) {

        return neo4jRepo.getConfidence(intentName,commandName);
    }

    public List getAll()
        {
                return neo4jRepo.getAll();
        }
    public List updateConfidence(Map map) {
        if(map.size()==3){
                return neo4jRepo.updateRelationship((String) (map.get("intentName")), (String) (map.get("commandName")), (Integer) (map.get("confidence")));
        }
        throw new ParameterException("Enter all the required parameters");
    }


    public List addIntentAndCommand(Map map) {
        if(map.size()==5){
            return neo4jRepo.addIntentAndCommand((String) map.get("commandName"),(List) map.get("commandParameter"),(String) (map.get("intentName")), IntentStatus.valueOf((String) (map.get("intentStatus"))),50,(String) (map.get("relationshipName")));
        }
        else if(map.size()==4){
            return neo4jRepo.addIntentAndCommand((String) map.get("commandName"),(List) map.get("commandParameter"),(String) (map.get("intentName")), IntentStatus.valueOf((String) (map.get("intentStatus"))),50);
        }
        throw new ParameterException("Enter all the required parameters");
    }

    public List getCommandByName(String intentName,String relationshipName) {
        List<JSONObject> list;
        if(relationshipName==null){
            list=neo4jRepo.getCommandByName(intentName);
        }
        else{
            System.out.println(neo4jRepo.getCommandByName(intentName,relationshipName));
            list=neo4jRepo.getCommandByName(intentName,relationshipName);
        }
       return list;
    }
}
