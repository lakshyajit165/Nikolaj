package com.stackroute.helpdesk.intentcommandmapping.repository;

import com.stackroute.helpdesk.intentcommandmapping.model.Command;
import com.stackroute.helpdesk.intentcommandmapping.model.IntentStatus;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Neo4jRepoTest {

    @Autowired
    Neo4jRepo neo4jRepo;

    private JSONObject jsonObject;
    private List<JSONObject> expectedData;
    private List<JSONObject> actualData;
    private List<String> commandParameter;

    @Before
    public void setUp(){
//        this.jsonObject=new JSONObject();
//        this.expectedData=new ArrayList<JSONObject>();
//        this.actualData=new ArrayList<JSONObject>();
//        this.commandParameter=new ArrayList<String>();
//        neo4jRepo.addIntent("intent", IntentStatus.MATURE);
    }

    @Test
    public void addIntent() {
//        jsonObject.put("Intent status","MATURE");
//        jsonObject.put("Intent name","intent");
//        expectedData.add(jsonObject);
//        assertEquals(expectedData,neo4jRepo.getAllIntents());
    }


    @Test
    public void getAllIntents() {
//        jsonObject.put("Intent status","MATURE");
//        jsonObject.put("Intent name","intent");
//        expectedData.add(jsonObject);
//        assertEquals(expectedData,neo4jRepo.getAllIntents());
    }


    @Test
    public void updateIntentStatus() {
//        jsonObject.put("Intent status","BABY");
//        jsonObject.put("Intent name","intent");
//        expectedData.add(jsonObject);
//        actualData=neo4jRepo.updateIntentStatus("intent", IntentStatus.BABY);
//        assertEquals(expectedData,actualData);
    }

    @Test
    public void addCommand() {
//        List<String> commandParameter=new ArrayList<String>();
//        commandParameter.add("param");
//        jsonObject.put("Command parameter",commandParameter);
//        jsonObject.put("Command name","commandName");
//        expectedData.add(jsonObject);
//        actualData=neo4jRepo.addCommand("commandName",commandParameter,);
//        assertEquals(expectedData,actualData);
    }

    @Test
    public void updateCommandParameter() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void addIntentAndCommand() {
    }

    @Test
    public void updateRelationship() {
    }

    @Test
    public void getCommandByName() {
    }

    @Test
    public void getConfidence() {
    }
}