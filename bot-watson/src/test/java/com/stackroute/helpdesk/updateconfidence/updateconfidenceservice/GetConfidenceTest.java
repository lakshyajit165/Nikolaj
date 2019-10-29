package com.stackroute.helpdesk.updateconfidence.updateconfidenceservice;


import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;
import com.stackroute.helpdesk.updateconfidence.exceptionclass.NoMatchFound;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetConfidenceTest {

    @Autowired
    GetConfidence getConfidence;

    @MockBean
    Neo4jService neo4jService;

    private List<JSONObject> list;

    @Before
    public void setUp(){
        list=new ArrayList<JSONObject>();
    }

    @Test
    public void getConfidence() {
        JSONObject object=new JSONObject();
        Integer confidence=50;
        object.put("e.confidence",50L);
        list.add(object);
        when(neo4jService.getConfidence("iname1","cname1")).thenReturn(list);
        assertEquals(confidence,getConfidence.getConfidence("iname1","cname1","rname1"));
        confidence=0;
        assertNotEquals(confidence,getConfidence.getConfidence("iname1","cname1","rname1"));
    }
    @Test(expected = NoMatchFound.class)
    public void getConfidenceException(){
        Integer confidence=50;
        when(neo4jService.getConfidence("iname1","cname1")).thenReturn(list);
        assertNotEquals(confidence,getConfidence.getConfidence("iname1","cname1","rname1"));
        getConfidence.getConfidence("iname1","cname1","rname1");
    }

}