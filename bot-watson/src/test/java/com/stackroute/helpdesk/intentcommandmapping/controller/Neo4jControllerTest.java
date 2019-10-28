package com.stackroute.helpdesk.intentcommandmapping.controller;


import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.ParameterException;
import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.Intent;
import com.stackroute.helpdesk.intentcommandmapping.model.IntentStatus;
import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;
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
    Neo4jController neo4jController;

    @MockBean
    private Neo4jService neo4jService;
    private Map map;
    private List<String> list;
    private String testParameter="testing";

    @Before
    public void setUp(){
        this.map=new HashMap();
        this.list= new ArrayList<String>();
        list.add(testParameter);
    }

    @Test
    public void getAllIntents() {
        when(neo4jService.getAllIntents()).thenReturn(list);
        assertEquals(list, neo4jController.getAllIntents().getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.getAllIntents().getBody().get("result"));
    }



    @Test()
    public void addIntent() {
        Intent intent=new Intent("testing",IntentStatus.MATURE);
        when(neo4jService.addIntent(intent)).thenReturn(list);
        assertEquals(list, neo4jController.addIntent(intent).getBody().get("result"));
        assertNotEquals("wrong Data",neo4jController.addIntent(intent).getBody().get("result"));
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
    }

    @Test
    public void updateIntentStatus() {
        Intent intent=new Intent("testing",IntentStatus.MATURE);
        when(neo4jService.updateIntentStatus(intent)).thenReturn(list);
        assertEquals(list, neo4jController.updateIntentStatus(intent).getBody().get("result"));
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


    @Test
    public void addIntentAndCommand() {
        when(neo4jService.addIntentAndCommand(map)).thenReturn(list);
        assertEquals(list, neo4jController.addIntentAndCommand(map).getBody().get("result"));
        assertNotEquals("wrong data",neo4jController.addIntentAndCommand(map).getBody().get("result"));
    }


    @Test
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


}