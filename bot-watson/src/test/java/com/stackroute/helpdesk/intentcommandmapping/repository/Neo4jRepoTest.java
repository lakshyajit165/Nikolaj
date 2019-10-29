package com.stackroute.helpdesk.intentcommandmapping.repository;

<<<<<<< HEAD
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import org.neo4j.test.TestGraphDatabaseFactory;
//import org.neo4j.test.rule.TestDirectory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jRepoTest {
     @Test
    public void getAllIntents() {

    }

//    @Autowired
//    Neo4jRepo neo4jRepo;
//
//    @Rule
//    public TestDirectory testDirectory = TestDirectory.testDirectory();
//    protected GraphDatabaseService graphDb;
//    private JSONObject item;
//    private Map map;
//    private List<JSONObject> list;
//
//    @Before
//    public void setUp(){
////        graphDb = new TestGraphDatabaseFactory().newImpermanentDatabase( testDirectory.directory() );
//        neo4jRepo.deleteRelationship();
//        neo4jRepo.deleteIntent();
//        neo4jRepo.deleteCommand();
//        this.item = new JSONObject();
//        this.map=new HashMap();
//        this.list= new ArrayList<>();
//    }
////    @After
////    public void destroyTestDatabase()
////    {
////        graphDb.shutdown();
////    }
////    @Test
////    public void shouldCreateNode() {
////        // tag::unitTest[]
//////        Node n;
//////        try (Transaction tx = graphDb.beginTx()) {
//////            n = graphDb.createNode();
//////            n.setProperty("name", "Nancy");
////////            n= (Node) neo4jRepo.addIntent("test","ok");
//////            tx.success();
//////        }
//////
//////        // The node should have a valid id
//////        assertThat(n.getId(), is(greaterThan(-1L)));
//////
//////        // Retrieve a node by using the id of the created node. The id's and
//////        // property should match.
//////        try (Transaction tx = graphDb.beginTx()) {
//////            Node foundNode = graphDb.getNodeById(n.getId());
//////            assertThat(foundNode.getId(), is(n.getId()));
//////            assertThat((String) foundNode.getProperty("name"), is("Nancy"));
//////        }
////        neo4jRepo.addIntent("test","ok");
////
////    }
//
//    @Test
//    public void getAllIntents() {
//        neo4jRepo.addIntent("iname1","istatus1");
//        item.put("Intent name","iname1");
//        item.put("Intent status","istatus1");
//        list.add(item);
//        assertEquals(list,neo4jRepo.getAllIntents());
//    }
//
//    @Test
//    public void addIntent() {
//        neo4jRepo.addIntent("iname1","istatus1");
//        item.put("Intent name","iname1");
//        item.put("Intent status","istatus1");
//        list.add(item);
//        assertEquals(list,neo4jRepo.getAllIntents());
//    }
//
//
//    @Test
//    public void updateIntentStatus() {
//        neo4jRepo.addIntent("iname1","istatus1");
//        item.put("Intent name","iname1");
//        item.put("Intent status","istatusupdated");
//        list.add(item);
//        assertEquals(list, neo4jRepo.updateIntentStatus("iname1","istatusupdated"));
//    }
//
////    @Test
////    public void getAllCommands() {
////        neo4jRepo.addIntent("iname1","istatus1");
////        item.put("Command name","cname1");
////        item.put("Command parameter","cparam1");
////        list.add(item);
////        neo4jRepo.addCommand("cname1","cparam1","iname1",10);
////        assertEquals(list,neo4jRepo.getAllCommands());
////    }
//
//    @Test
//    public void addCommand() {
//        neo4jRepo.addIntent("iname1","istatus1");
//        neo4jRepo.addCommand("cname1","cparam1","iname1",10,"rname1");
//        item.put("Command name","cname1");
//        item.put("Command parameter","cparam1");
//        list.add(item);
//        assertEquals(list,neo4jRepo.getAllCommands());
//    }
//
//    @Test
//    public void updateCommand() {
//        neo4jRepo.addIntent("iname1","istatus1");
//        neo4jRepo.addCommand("cname1","cparam1","iname1",10,"rname1");
//        item.put("Command name","cname1");
//        item.put("Command parameter","cparamupdated");
//        list.add(item);
//        assertEquals(list,neo4jRepo.updateCommandParameter("cname1","cparamupdated"));
//    }
//
//    @Test
//    public void getAll() {
//        neo4jRepo.addIntentAndCommand("cname1","cparam1","iname1","istatus1",100,"rname1");
//        item.put("Command parameter","cparam1");
//        item.put("Intent status","istatus1");
//        item.put("Intent name","iname1");
//        item.put("Confidence",100);
//        item.put("Command name","cname1");
//        item.put("Relationship","rname1");
//        list.add(item);
//        assertEquals(neo4jRepo.getAll().toString().trim(),list.toString());
//    }
//
//
//
//    @Test
//    public void addIntentCommand() {
//        neo4jRepo.addIntentAndCommand("cname1","cparam1","iname1","istatus1",100,"rname1");
//        item.put("Command parameter","cparam1");
//        item.put("Intent status","istatus1");
//        item.put("Intent name","iname1");
//        item.put("Confidence",100);
//        item.put("Command name","cname1");
//        item.put("Relationship","rname1");
//        list.add(item);
//        assertEquals(neo4jRepo.getAll().toString().trim(),list.toString());
//    }
//
//    @Test
//    public void updateRelationship() {
//        neo4jRepo.addIntentAndCommand("cname1","cparam1","iname1","istatus1",0,"rname1");
//        item.put("Intent name","iname1");
//        item.put("Confidence",100);
//        item.put("Command name","cname1");
//        list.add(item);
//        assertEquals(neo4jRepo.updateRelationship("iname1","cname1",100).toString().trim(),list.toString());
//    }
//
//    @Test
//    public void getCommandByName() {
//        neo4jRepo.addIntentAndCommand("cname1","cparam1","iname1","istatus1",100,"rname1");
//        neo4jRepo.addCommand("cname2","cparam2","iname1",90,"rname2");
//        item.put("Command parameter","cparam1");
//        item.put("Command name","cname1");
//        list.add(item);
//        assertEquals(neo4jRepo.getCommandByName("iname1","rname1").toString().trim(),list.toString());
//        assertEquals(neo4jRepo.getCommandByName("iname1").toString().trim(),list.toString());
//
//    }

//    @Test
//    public void deleteCommand() {
//    }

=======
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
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
}