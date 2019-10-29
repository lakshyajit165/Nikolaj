package com.stackroute.helpdesk.intentcommandmapping.controller;


import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.ParameterException;
import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.Intent;
import com.stackroute.helpdesk.intentcommandmapping.model.IntentStatus;
import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;
<<<<<<< HEAD
=======
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.IntrospectionException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jControllerTest {

    @Autowired
<<<<<<< HEAD
    Neo4jController neo4jController;

    @MockBean
    private Neo4jService neo4jService;
    private Map map;
    private List<String> list;
    private String testParameter="testing";
=======
    private Neo4jController neo4jController;

    @MockBean
    private Neo4jService neo4jService;

    private Map map;
    private List<String> list;
    private String testParameter="testing";
    private JSONObject item;
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e

    @Before
    public void setUp(){
        this.map=new HashMap();
        this.list= new ArrayList<String>();
        list.add(testParameter);
<<<<<<< HEAD
=======
        item=new JSONObject();
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    }

    @Test
    public void getAllIntents() {
        when(neo4jService.getAllIntents()).thenReturn(list);
        assertEquals(list, neo4jController.getAllIntents().getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.getAllIntents().getBody().get("result"));
    }

<<<<<<< HEAD


    @Test()
=======
    @Test
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    public void addIntent() {
        Intent intent=new Intent("testing",IntentStatus.MATURE);
        when(neo4jService.addIntent(intent)).thenReturn(list);
        assertEquals(list, neo4jController.addIntent(intent).getBody().get("result"));
        assertNotEquals("wrong Data",neo4jController.addIntent(intent).getBody().get("result"));
<<<<<<< HEAD
//
//        when(neo4jService.addIntent(intent)).thenReturn(Collections.singletonList(ParameterException.class));
//        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.addIntent(intent).getBody().get("result"));
//        intent=new Intent("intentName",null);
//        when(neo4jService.addIntent(intent)).thenReturn(Collections.singletonList(ParameterException.class));
//        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.addIntent(intent).getBody().get("result"));
//        intent=new Intent(null,IntentStatus.MATURE);
//        when(neo4jService.addIntent(intent)).thenReturn(Collections.singletonList(ParameterException.class));
//        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.addIntent(intent).getBody().get("result"));
//        intent=new Intent(null,null);
//        when(neo4jService.addIntent(intent)).thenReturn(Collections.singletonList(ParameterException.class));
//        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.addIntent(intent).getBody().get("result"));
=======
    }
    @Test
    public void addEmptyIntent(){
        Intent intent=new Intent();
        list.clear();
        assertEquals(list,neo4jController.addIntent(intent).getBody().get("result"));
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    }

    @Test
    public void updateIntentStatus() {
        Intent intent=new Intent("testing",IntentStatus.MATURE);
        when(neo4jService.updateIntentStatus(intent)).thenReturn(list);
        assertEquals(list, neo4jController.updateIntentStatus(intent).getBody().get("result"));
<<<<<<< HEAD
        assertNotEquals("list", neo4jController.updateIntentStatus(intent).getBody().get("result"));
        intent=new Intent();
        when(neo4jService.updateIntentStatus(intent)).thenReturn(Collections.singletonList(ParameterException.class));
        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.updateIntentStatus(intent).getBody().get("result"));
        intent=new Intent("intentName",null);
        when(neo4jService.updateIntentStatus(intent)).thenReturn(Collections.singletonList(ParameterException.class));
        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.updateIntentStatus(intent).getBody().get("result"));
        intent=new Intent(null,IntentStatus.MATURE);
        when(neo4jService.updateIntentStatus(intent)).thenReturn(Collections.singletonList(ParameterException.class));
        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.updateIntentStatus(intent).getBody().get("result"));
        intent=new Intent(null,null);
        when(neo4jService.updateIntentStatus(intent)).thenReturn(Collections.singletonList(ParameterException.class));
        assertEquals(Collections.singletonList(ParameterException.class), neo4jController.updateIntentStatus(intent).getBody().get("result"));
    }

    @Test
    public void addCommand() {
        when(neo4jService.addCommand(map)).thenReturn(list);
        assertEquals(list, neo4jController.addCommand(map).getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.addCommand(map).getBody().get("result"));
=======
    }
    @Test
    public void addEmptyIntentForIntentStatus(){
        Intent intent=new Intent();
        list.clear();
        when(neo4jService.addIntent(intent)).thenReturn(list);
        assertEquals(list,neo4jController.addIntent(intent).getBody().get("result"));
    }

    @Test
    public void addCommandWithEmptyData() {
        list.clear();
        when(neo4jService.addCommand(map)).thenReturn(list);
        assertEquals(list, neo4jController.addCommand(map).getBody().get("result"));
    }
    @Test
    public void addCommandWithRelationship() {
        List command=new ArrayList<String>();
        List addObject=new ArrayList();
        command.add("caparam1");
        map.put("intentName",command);
        map.put("commandName","cname1");
        map.put("commandParameter",command);
        map.put("relationship","relationshipName");
        item.put("Intent name","iname1");
        item.put("Confidence",50);
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        item.put("relationship","relationshipName");
        addObject.add(item);
        when(neo4jService.addCommand(map)).thenReturn(addObject);
        System.out.println(neo4jController.addCommand(map));
        assertEquals(addObject, neo4jController.addCommand(map).getBody().get("result"));
    }
    @Test
    public void addCommandWithoutRelationship() {
        List command=new ArrayList<String>();
        List addCommand=new ArrayList();
        command.add("caparam1");
        map.put("intentName",command);
        map.put("commandName","cname1");
        map.put("commandParameter",command);
        item.put("Intent name","iname1");
        item.put("Confidence",50);
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        addCommand.add(item);
        when(neo4jService.addCommand(map)).thenReturn(addCommand);
        assertEquals(addCommand, neo4jController.addCommand(map).getBody().get("result"));
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    }

    @Test
    public void updateCommandParameter() {
        List list=new ArrayList();
        list.add("name");
        Command command=new Command("testing",list);
        when(neo4jService.updateCommandParameter(command)).thenReturn(list);
        assertEquals(list, neo4jController.updateCommandParameter(command).getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.updateCommandParameter(command).getBody().get("result"));
    }
<<<<<<< HEAD
=======
    @Test
    public void updateCommandParameterWithEmptyData() {
        Command command=new Command();
        list.clear();
        when(neo4jService.updateCommandParameter(command)).thenReturn(list);
        assertEquals(list, neo4jController.updateCommandParameter(command).getBody().get("result"));
//        assertNotEquals("wrong data",neo4jController.updateCommandParameter(command).getBody().get("result"));
    }
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e


    @Test
    public void addIntentAndCommand() {
<<<<<<< HEAD
        when(neo4jService.addIntentAndCommand(map)).thenReturn(list);
        assertEquals(list, neo4jController.addIntentAndCommand(map).getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.addIntentAndCommand(map).getBody().get("result"));
=======
        list.clear();
        when(neo4jService.addIntentAndCommand(map)).thenReturn(list);
        assertEquals(list, neo4jController.addIntentAndCommand(map).getBody().get("result"));
//        assertNotEquals("wrong data",neo4jController.addIntentAndCommand(map).getBody().get("result"));
    }
    @Test
    public void addIntentAndCommandWithRelationship(){
        List result=new ArrayList();
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
        result.add(item);
        when(neo4jService.addIntentAndCommand(map)).thenReturn(result);
        assertEquals(result, neo4jController.addIntentAndCommand(map).getBody().get("result"));
    }

    @Test
    public void addIntentAndCommandWithoutRelationship(){
        List result=new ArrayList();
        map.put("intentName","iname1");
        map.put("intentStatus","MATURE");
        map.put("commandName","cname1");
        map.put("commandParameter",list);
        item.put("Intent name","iname1");
        item.put("Intent status","MATURE");
        item.put("Confidence",50);
        item.put("Command name","cname1");
        item.put("Command parameter","cparam1");
        result.add(item);
        when(neo4jService.addIntentAndCommand(map)).thenReturn(result);
        assertEquals(result, neo4jController.addIntentAndCommand(map).getBody().get("result"));
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    }


    @Test
<<<<<<< HEAD
    public void updateRelationship() {
        when(neo4jService.updateConfidence(map)).thenReturn(list);
        assertEquals(list, neo4jController.updateConfidence(map).getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.updateConfidence(map).getBody().get("result"));
    }

    @Test
    public void getCommandByName(){
        when(neo4jService.getCommandByName("intentName","relationship")).thenReturn(list);
        assertEquals(list,neo4jController.getCommandByName("intentName","relationship").getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.getCommandByName("intenetName","relationship").getBody().get("result"));

        list.add("commandNameWithIntent");
        when(neo4jService.getCommandByName("intentName",null)).thenReturn(list);
        assertEquals(list,neo4jController.getCommandByName("intentName",null).getBody().get("result"));
        assertNotEquals("wrong text",neo4jController.getCommandByName("intentName",null));
    }


=======
    public void updateConfidence() {
        List data=new ArrayList();
        map.put("intentName","iname1");
        map.put("commandName","cname1");
        map.put("confidence",50);
        data.add(map);
        when(neo4jService.updateConfidence(map)).thenReturn(data);
        assertEquals(data, neo4jController.updateConfidence(map).getBody().get("result"));
    }

    @Test
    public void getAllWithEmptyData(){
        when(neo4jService.getAll()).thenReturn(list);
        assertEquals(list, neo4jController.getAll().getBody().get("result"));
    }

    @Test
    public void getCommandByName(){
        List data=new ArrayList();
        item.put("Confidence",50);
        item.put("Command name","commandName");
        item.put("Command parameter",list);
        data.add(item);
        when(neo4jService.getCommandByName("intentName","relationship")).thenReturn(data);
        assertEquals(data,neo4jController.getCommandByName("intentName","relationship").getBody().get("result"));
    }
    @Test
    public void getCommandByNameWithoutRelationship(){
        List data=new ArrayList();
        item.put("Confidence",50);
        item.put("Command name","commandName");
        item.put("Command parameter",list);
        data.add(item);
        when(neo4jService.getCommandByName("intentName",null)).thenReturn(data);
        assertEquals(data,neo4jController.getCommandByName("intentName",null).getBody().get("result"));
//        assertNotEquals("wrong text",neo4jController.getCommandByName("intentName",null));
    }



>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
}