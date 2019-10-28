package com.stackroute.helpdesk.intentcommandmapping.service;


import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.DataAlreadyExists;
import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.ParameterException;
import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.Intent;
import com.stackroute.helpdesk.intentcommandmapping.model.IntentStatus;
import com.stackroute.helpdesk.intentcommandmapping.repository.Neo4jRepo;
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
        assertEquals(1, neo4jService.getAllIntents().size());
        assertNotEquals(2,neo4jService.getAllIntents().size());

    }

    @Test(expected = ParameterException.class)
    public void addIntent() {

        Intent intent=new Intent("testing",IntentStatus.MATURE);
        item.put("Intent name","iname1");
        item.put("Intent status","MATURE");
        list.add(item);
        System.out.println(list);
        when(neo4jRepo.addIntent(intent.getIntentName(),intent.getIntentStatus())).thenReturn(list);
        assertEquals(1, neo4jService.addIntent(intent).size());
        assertNotEquals(2,neo4jService.addIntent(intent).size());
        intent=new Intent();
        neo4jService.addIntent(intent);

    }


    @Test(expected = ParameterException.class)
    public void updateIntentStatus() {
        Intent intent=new Intent("testing",IntentStatus.MATURE);
        item.put("Intent name","iname1");
        item.put("Intent status","MATURE");
        list.add(item);
        when(neo4jRepo.updateIntentStatus(intent.getIntentName(),intent.getIntentStatus())).thenReturn(list);
        assertEquals(1, neo4jService.updateIntentStatus(intent).size());
        assertNotEquals(2,neo4jService.updateIntentStatus(intent).size());
        intent=new Intent();
        neo4jService.updateIntentStatus(intent);
        intent=new Intent("intentName",null);
        neo4jService.updateIntentStatus(intent);
        intent=new Intent(null,IntentStatus.MATURE);
        neo4jService.updateIntentStatus(intent);
        intent=new Intent(null,null);
        neo4jService.updateIntentStatus(intent);
            }

    @Test(expected = ParameterException.class)
    public void addCommand() {
        List command=new ArrayList<String>();
        command.add("caparam1");
        map.put("intentName",command);
        map.put("commandName","cname1");
        map.put("commandParameter",command);
        map.put("relationship","relationshipName");
        item.put("Intent name","iname1");
        item.put("Confidence",50);
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        list.add(item);
        when(neo4jRepo.addCommand((String) map.get("commandName"),(List) map.get("commandParameter"),(List) (map.get("intentName")),50,(String) (map.get("relationshipName")))).thenReturn(list);
        assertEquals(1, neo4jService.addCommand(map).size());
        assertNotEquals(2,neo4jService.addCommand(map).size());
        map.clear();
        neo4jService.addCommand(map);

    }

    @Test(expected = ParameterException.class)
    public void updateCommand() {
        List commandParameter=new ArrayList<String>();
        commandParameter.add("caparam1");
        Command testCommand=new Command("commandName",commandParameter);
        item.put("Command name","cnew1");
        item.put("Command parameter","cparam1");
        list.add(item);
        when(neo4jRepo.updateCommandParameter("commandName",commandParameter)).thenReturn(list);
        assertEquals(1, neo4jService.updateCommandParameter(testCommand).size());
        assertNotEquals(2, neo4jService.updateCommandParameter(testCommand).size());
        testCommand=new Command(null,commandParameter);
        neo4jService.updateCommandParameter(testCommand);
        testCommand=new Command("commandName",null);
        neo4jService.updateCommandParameter(testCommand);
        testCommand=new Command(null,null);
        neo4jService.updateCommandParameter(testCommand);
    }

    @Test
    public void getAll() {

//        item.put("Intent name","iname1");
//        item.put("Intent status","istatus1");
//        item.put("Confidence",10);
//        item.put("Command name","cname1");
//        item.put("Command parameter","cparam1");
//        item.put("Relationship","relationshipName");
//        list.add(item);
//        when(neo4jRepo.getAll()).thenReturn(list);
//        assertEquals(1, neo4jService.getAll().size());
//        assertNotEquals();

    }


    @Test
    public void addIntentAndCommand() {
        List command=new ArrayList<String>();
        command.add("caparam1");
        map.put("intentName","iname1");
        map.put("intentStatus","MATURE");
        map.put("commandName","cname1");
        map.put("commandParameter",list);
        map.put("relationship","relationshipName");
        item.put("Relationship","relationshipName");
        item.put("Intent name","iname1");
        item.put("Intent status","MATURE");
        item.put("Confidence",50);
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        list.add(item);
        when(neo4jRepo.addIntentAndCommand((String) map.get("commandName"),(List) map.get("commandParameter"),(String) (map.get("intentName")), IntentStatus.valueOf ((String) map.get("intentStatus")),50,(String) (map.get("relationshipName")))).thenReturn(list);
        assertEquals(1, neo4jService.addIntentAndCommand(map).size());
        assertNotEquals(2,neo4jService.addIntentAndCommand(map).size());
    }

    @Test
    public void getCommandByName(){
//        map.put("intentName","iname1");
//        map.put("relationship","relationshipName");
//        item.put("Command name","cname1");
//        item.put("Command parameter","cparam1");
//        list.add(item);
//        when(neo4jRepo.getCommandByName((String) (map.get("intentName")), (String) (map.get("relationshipName")))).thenReturn(list);
//        assertEquals(1, neo4jService.getCommandByName(map).size());
//        when(neo4jRepo.getCommandByName((String) (map.get("intentName")))).thenReturn(list);
//        assertEquals(1, neo4jService.getCommandByName(map).size());
//        assertNotEquals(2,neo4jService.getCommandByName(map).size());
    }

////    @Test
////    void delete() {
////    }
//
//
//    @Test
//    public void updateConfidence() {
//
//        map.put("Intent name","iname1");
//        map.put("Confidence",10);
//        map.put("Command name","cname1");
//
//        item.put("Intent name","iname1");
//        item.put("Confidence",10);
//        item.put("Command name","cname1");
//
//        list.add(item);
//        when(neo4jRepo.updateConfidence((String) (map.get("intentName")),(String) (map.get("commandName")),(Integer) (map.get("confidence")))).thenReturn(list);
//        assertEquals(1, neo4jService.updateConfidence(map).size());
//    }
//
//    @Test
//    void deleteCommand() {
//    }
//



    }