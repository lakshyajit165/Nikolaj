package com.stackroute.helpdesk.intentcommandmapping.service;

import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.Intent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface Neo4jServiceRepo {
    List getAllIntents();
    List addIntent(Intent intent);
    List updateIntentStatus(Intent intent);
    List updateCommandParameter(Command command);
    List addCommand(Map map);
//    List getConfidence(String intentName, String commandName, String relationshipName);
    List updateConfidence(Map map);
    List addIntentAndCommand(Map map);
    public List getCommandByName(String intentName, String relationshipName);
    List getAll();
    List getConfidence(String intentName, String commandName);
}
