package com.stackroute.helpdesk.intentcommandmapping.service;


import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.DataAlreadyExists;
import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.ParameterException;
import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.Intent;
import com.stackroute.helpdesk.intentcommandmapping.model.IntentStatus;
import com.stackroute.helpdesk.intentcommandmapping.repository.Neo4jRepo;
import io.swagger.models.auth.In;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jServiceTest {

    @Autowired
    Neo4jService neo4jService;

    @MockBean
    private Neo4jRepo neo4jRepo;

    private JSONObject item;
    private Map map;
    private List<JSONObject> list;

    @Before
    public void setUp(){
         this.item = new JSONObject();
         this.map=new HashMap();
         this.list= new ArrayList<>();
    }

    @Test
    public void getAllIntents() {

        item.put("Intent name","iname1");
        item.put("Intent status", IntentStatus.MATURE);
        list.add(item);
        when(neo4jRepo.getAllIntents()).thenReturn(list);
        assertEquals(list, neo4jService.getAllIntents());
    }

    @Test
    public void addIntent() {
        Intent intent=new Intent("testing",IntentStatus.MATURE);
        item.put("Intent name","iname1");
        item.put("Intent status","MATURE");
        list.add(item);
        when(neo4jRepo.addIntent(intent.getIntentName(),intent.getIntentStatus())).thenReturn(list);
        assertEquals(list, neo4jService.addIntent(intent));
    }

    @Test(expected = ParameterException.class)
    public void addIntentWithParameterException() {
        Intent intent=new Intent();
        neo4jService.addIntent(intent);
        intent=new Intent("intentName",null);
        neo4jService.addIntent(intent);
        intent=new Intent(null,IntentStatus.MATURE);
        neo4jService.addIntent(intent);
        intent=new Intent(null,null);
        neo4jService.addIntent(intent);
    }

    @Test
    public void updateIntentStatus() {
        Intent intent=new Intent("testing",IntentStatus.MATURE);
        item.put("Intent name","iname1");
        item.put("Intent status","MATURE");
        list.add(item);
        when(neo4jRepo.updateIntentStatus(intent.getIntentName(),intent.getIntentStatus())).thenReturn(list);
        assertEquals(list,neo4jService.updateIntentStatus(intent));
    }
    @Test(expected = ParameterException.class)
    public void updateIntentStatusWithParameterException() {
        Intent intent=new Intent();
        neo4jService.updateIntentStatus(intent);
        intent=new Intent("intentName",null);
        neo4jService.updateIntentStatus(intent);
        intent=new Intent(null,IntentStatus.MATURE);
        neo4jService.updateIntentStatus(intent);
        intent=new Intent(null,null);
        neo4jService.updateIntentStatus(intent);
    }

    @Test
    public void addCommandWithRelationship() {
        List command=new ArrayList<String>();
        command.add("caparam1");
        map.put("intentName",command);
        map.put("commandName","cname1");
        map.put("commandParameter",command);
        map.put("relationship","relationshipName");
        item.put("relationship","relationshipName");
        item.put("Intent name","iname1");
        item.put("Confidence",50);
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        list.add(item);
        when(neo4jRepo.addCommand((String) map.get("commandName"),(List) map.get("commandParameter"),(List) (map.get("intentName")),50,(String) (map.get("relationshipName")))).thenReturn(list);
        assertEquals(list, neo4jService.addCommand(map));
    }
    @Test
    public void addCommandWithoutRelationship() {
        List command=new ArrayList<String>();
        command.add("caparam1");
        map.put("intentName",command);
        map.put("commandName","commandName");
        map.put("commandParameter",command);
        item.put("Intent name",command);
        item.put("Confidence",50);
        item.put("Command name","commandName");
        item.put("Command parameter",command);
        list.add(item);
        when(neo4jRepo.addCommand("commandName",command,command,50)).thenReturn(list);
        assertEquals(list, neo4jService.addCommand(map));
    }
    @Test(expected = ParameterException.class)
    public void addCommandWithParameterException() {
        neo4jService.addCommand(map);
    }

    @Test
    public void updateCommand() {
        List commandParameter=new ArrayList<String>();
        commandParameter.add("caparam1");
        Command testCommand=new Command("commandName",commandParameter);
        item.put("Command name","cnew1");
        item.put("Command parameter","cparam1");
        list.add(item);
        when(neo4jRepo.updateCommandParameter("commandName",commandParameter)).thenReturn(list);
        assertEquals(list, neo4jService.updateCommandParameter(testCommand));
    }

    @Test(expected = ParameterException.class)
    public void updateCommandWithParameterException() {
        List commandParameter=new ArrayList<String>();
        commandParameter.add("caparam1");
        Command testCommand=new Command("commandName",commandParameter);
        testCommand=new Command(null,commandParameter);
        neo4jService.updateCommandParameter(testCommand);
        testCommand=new Command("commandName",null);
        neo4jService.updateCommandParameter(testCommand);
        testCommand=new Command(null,null);
        neo4jService.updateCommandParameter(testCommand);
    }

    @Test
    public void getAll() {
        item.put("Intent name","iname1");
        item.put("Intent status","istatus1");
        item.put("Confidence",10);
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        item.put("Relationship","relationshipName");
        list.add(item);
        when(neo4jRepo.getAll()).thenReturn(list);
        assertEquals(list, neo4jService.getAll());
    }

    @Test
    public void addIntentAndCommandWithRelationshipName() {
        List command=new ArrayList<String>();
        command.add("caparam1");
        map.put("intentName","intentName");
        map.put("intentStatus","MATURE");
        map.put("commandName","commandName");
        map.put("commandParameter",command);
        map.put("relationshipName","relationshipName");
        item.put("Relationship","relationshipName");
        item.put("Intent name","intentName");
        item.put("Intent status","MATURE");
        item.put("Confidence",50);
        item.put("Command name","commandName");
        item.put("Command parameter",command);
        list.add(item);
        when(neo4jRepo.addIntentAndCommand("commandName",command,"intentName", IntentStatus.valueOf ((String) map.get("intentStatus")),50,"relationshipName")).thenReturn(list);
        assertEquals(list, neo4jService.addIntentAndCommand(map));
    }
    @Test
    public void addIntentAndCommandWithoutRelationshipName() {
        List command=new ArrayList<String>();
        command.add("caparam1");
        map.put("intentName","intentName");
        map.put("intentStatus","MATURE");
        map.put("commandName","commandName");
        map.put("commandParameter",command);
        item.put("Intent name","intentName");
        item.put("Intent status","MATURE");
        item.put("Confidence",50);
        item.put("Command name","commandName");
        item.put("Command parameter","cparam1");
        list.add(item);
        when(neo4jRepo.addIntentAndCommand("commandName",command,"intentName", IntentStatus.valueOf ((String) map.get("intentStatus")),50)).thenReturn(list);
        assertEquals(list, neo4jService.addIntentAndCommand(map));
    }
    @Test(expected = ParameterException.class)
    public void addIntentAndCommandParameterException() {
        neo4jService.addIntentAndCommand(map);
    }

    @Test
    public void getCommandByIntentName(){
        item.put("Command name","commandName");
        item.put("Command parameter","commandParam");
        item.put("Confidence",50);
        list.add(item);
        when(neo4jRepo.getCommandByName("intentName")).thenReturn(list);
        assertEquals(list, neo4jService.getCommandByName("intentName",null));
    }
    @Test
    public void getCommandByIntentNameAndRelationship(){
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        item.put("Confidence",50);
        list.add(item);
        when(neo4jRepo.getCommandByName("intentName","relationshipName")).thenReturn(list);
        assertEquals(list, neo4jService.getCommandByName("intentName","relationshipName"));
    }
    @Test
    public void updateConfidence() {
        map.put("intentName","intentName");
        map.put("commandName","commandName");
        map.put("confidence",70);
        item.put("Intent name","iname1");
        item.put("Confidence",70);
        item.put("Command name","cname1");
        list.add(item);
        when(neo4jRepo.updateRelationship("intentName","commandName",70)).thenReturn(list);
        assertEquals(list, neo4jService.updateConfidence(map));
    }
    @Test(expected = ParameterException.class)
    public void updateConfidenceParameterException() {
    neo4jService.updateConfidence(map);
    }
    @Test
    public void getConfidence(){
        item.put("Confidence",70);
        list.add(item);
        when(neo4jRepo.getConfidence("intentName","commandName")).thenReturn(list);
        assertEquals(list,neo4jService.getConfidence("intentName","commandName"));
    }
}