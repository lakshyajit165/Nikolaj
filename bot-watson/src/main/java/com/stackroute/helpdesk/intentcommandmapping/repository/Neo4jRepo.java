package com.stackroute.helpdesk.intentcommandmapping.repository;

import com.stackroute.helpdesk.intentcommandmapping.model.IntentStatus;
import org.json.simple.JSONObject;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Neo4jRepo extends Neo4jRepository{

    @Query("MATCH (i:Intent) RETURN i.intentName AS `Intent name`,i.intentStatus AS `Intent status`")
    List<JSONObject> getAllIntents();

    @Query("merge(i:Intent{intentName: {name},intentStatus: {status}}) RETURN i.intentName as `Intent name`, i.intentStatus as `Intent status`")
    List<JSONObject> addIntent(String name, IntentStatus status);

    @Query("match (i:Intent{intentName:{intentName}}) set i.intentStatus={intentStatus} return i.intentName as `Intent name`, i.intentStatus as `Intent status`")
    List<JSONObject> updateIntentStatus(String intentName, IntentStatus intentStatus);

    @Query("unwind range(0,size({intentName})) as index merge(c:Command {commandName:{commandName},commandParameter: {commandParameter}}) with c,{intentName} as ig,index MATCH (i:Intent {intentName: ig[index]}) merge (i)-[e:EXECUTED{confidence:{confidence},relationshipName:{relationshipName}}]->(c) RETURN i.intentName as `Intent name`, e.confidence as `Confidence`,e.relationshipName as `relationship`, c.commandName as `Command name`, c.commandParameter as `Command parameter`")
    List<JSONObject> addCommand(String commandName, List commandParameter, List intentName, Integer confidence, String relationshipName);

    @Query("unwind range(0,size({intentName})) as index merge(c:Command {commandName:{commandName},commandParameter: {commandParameter}}) with c,{intentName} as ig,index MATCH (i:Intent {intentName: ig[index]}) merge (i)-[e:EXECUTED{confidence:{confidence}}]->(c) RETURN i.intentName as `Intent name`, e.confidence as `Confidence`, c.commandName as `Command name`, c.commandParameter as `Command parameter`")
    List<JSONObject> addCommand(String commandName, List commandParameter, List intentName, Integer confidence);
    @Query("match (c:Command{commandName:{commandName}}) with c.commandParameter as c1,{commandParameter} as c2 unwind c1+c2 as c3 with collect(distinct c3) as c4 match (ca:Command{commandName:{commandName}}) set ca.commandParameter=c4 return ca.commandName as `Command name`,ca.commandParameter as `Command parameter`")
    List<JSONObject> updateCommandParameter(String commandName, List commandParameter);

    @Query("MATCH (i:Intent)-[r:EXECUTED]->(c:Command) RETURN i.intentName as `Intent name`, i.intentStatus as `Intent status`, r.confidence as `Confidence`,r.relationshipName as `Relationship`, c.commandName as `Command name`, c.commandParameter as `Command parameter`")
    List<JSONObject> getAll();

    @Query(" merge(i:Intent {intentName:{intentName},intentStatus: {intentStatus}}) merge(c:Command{commandName:{commandName},commandParameter:{commandParameter}}) merge (i)-[e:EXECUTED {confidence: {confidence},relationshipName:{relationshipName}}]->(c) return i.intentName as `Intent name`,i.intentStatus as `Intnet status`, e.confidence as `Confidence`, c.commandName as `Command name`, c.commandParameter as `Command parameter`,e.relationshipName as `Relationship`;")
    List<JSONObject> addIntentAndCommand(String commandName, List commandParameter, String intentName, IntentStatus intentStatus, Integer confidence, String relationshipName);

    @Query(" merge(i:Intent {intentName:{intentName},intentStatus: {intentStatus}}) merge(c:Command{commandName:{commandName},commandParameter:{commandParameter}}) merge (i)-[e:EXECUTED {confidence: {confidence}}]->(c) return i.intentName as `Intent name`,i.intentStatus as `Intnet status`, e.confidence as `Confidence`, c.commandName as `Command name`, c.commandParameter as `Command parameter`;")
    List<JSONObject> addIntentAndCommand(String commandName, List commandParameter, String intentName, IntentStatus intentStatus, Integer confidence);

    @Query("Match (i:Intent{intentName:{intentName}})-[e:EXECUTED]->(c:Command{commandName:{commandName}}) set e.confidence={confidence}    return i.intentName as `Intent name`, e.confidence as `Confidence`, c.commandName as `Command name`")
    List<JSONObject> updateRelationship(String intentName, String commandName, Integer confidence);

//
//    @Query(" create(i:Intent {intentName:{intentName},intentStatus: {intentStatus}}) create(c:Command{commandName:{commandName},commandParameter:{commandParameter}}) create (i)-[e:EXECUTED {confidence: {confidence}}]->(c) return i.intentName as `Intent name`,i.intentStatus as `Intnet status`, e.confidence as `Confidence`, c.commandName as `Command name`, c.commandParameter as `Command parameter`;")
//    List<JSONObject> addIntentAndCommand(String commandName,String commandParameter,String intentName,String intentStatus,Integer confidence);
//

    @Query("MATCH (i:Intent)-[e:EXECUTED]->(c:Command) where i.intentName={intentName} and e.relationshipName={relationshipName} return c.commandName as `Command name`,c.commandParameter as `Command parameter` order by e.confidence desc limit 1;")
    List<JSONObject> getCommandByName(String intentName, String relationshipName);

    @Query("MATCH (i:Intent)-[e:EXECUTED]->(c:Command) where i.intentName={intentName} return c.commandName as `Command name`,c.commandParameter as `Command parameter` order by e.confidence desc limit 1;")
    List<JSONObject> getCommandByName(String intentName);

    @Query("MATCH p=(Intent)-[:EXECUTED]->(Command) delete p ")
    List<JSONObject> deleteRelationship();

    @Query("Match(i:Intent{intentName:{intentName}})-[e:EXECUTED]->(c:Command{commandName:{commandName}}) delete e return e")
    List<JSONObject> deleteRelation(String intentName, String commandName);

    @Query("MATCH (c:Command) delete c ")
    void deleteCommand();

    @Query("MATCH (c:Command) RETURN c.commandName as `Command name`,c.commandParameter as `Command parameter`")
    List<JSONObject> getAllCommands();

    @Query("MATCH (i:Intent) delete i")
    void deleteIntent();

    @Query("MATCH (i:Intent)-[e:EXECUTED]->(c:Command) where i.intentName={intentName} and c.commandName={commandName} return e.confidence order by e.confidence desc limit 1;")
    List<JSONObject> getConfidence(String intentName, String commandName);

//    @Query("match(n:Intent)\n" +
//            "where n.intentName={intentName}\n" +
//            "return true")
//    boolean checkIntent(String intentName);
//
//    @Query("match(n:Command)\n" +
//            "where n.commandName={commandName}\n" +
//            "return true")
//    boolean checkCommand(String commandName);
}
