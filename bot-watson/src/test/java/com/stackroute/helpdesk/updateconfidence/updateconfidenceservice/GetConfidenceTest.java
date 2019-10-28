package com.stackroute.helpdesk.updateconfidence.updateconfidenceservice;


import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetConfidenceTest {

    @Autowired
    GetConfidence getConfidence;

    @MockBean
    Neo4jService neo4jService;

    @Test
    public void getConfidence() {
        Integer confidence=50;
        JSONObject object=new JSONObject();
        object.put("e.confidence",50L);
        List list =new ArrayList<JSONObject>();
        list.add(object);
//        when(neo4jService.getConfidence("iname1","cname1","rname1")).thenReturn(list);
//        assertEquals(confidence,getConfidence.getConfidence("iname1","cname1","rname1"));

    }

}