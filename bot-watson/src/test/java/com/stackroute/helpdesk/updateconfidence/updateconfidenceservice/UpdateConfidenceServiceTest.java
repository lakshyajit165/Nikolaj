package com.stackroute.helpdesk.updateconfidence.updateconfidenceservice;

<<<<<<< HEAD
import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;
=======
import com.ibm.watson.natural_language_understanding.v1.model.Feed;
import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;
import com.stackroute.helpdesk.updateconfidence.model.Feedback;
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
<<<<<<< HEAD
=======
import static org.junit.Assert.assertNotEquals;
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateConfidenceServiceTest {

    @Autowired
    UpdateConfidenceService updateConfidenceService;

    @MockBean
    Neo4jService neo4jService;

    @MockBean
    GetConfidence getConfidence;

    @Test
    public void updateConfidence() {
        String response="confidence for intent and command updated sucessfully";
        Map map = null;
        List list=null;
<<<<<<< HEAD

//        when(getConfidence.getConfidence("iname1","cname1","rname1")).thenReturn(50);
//        when(neo4jService.updateConfidence(map)).thenReturn(list);
//        assertEquals(response,updateConfidenceService.updateConfidence("iname1","cname1","ranme1",5));

=======
        Feedback feedback=new Feedback("intentName","commandName",5);

        when(getConfidence.getConfidence("iname1","cname1",null)).thenReturn(50);
        when(neo4jService.updateConfidence(map)).thenReturn(list);
        assertEquals(response,updateConfidenceService.updateConfidence(feedback));
        assertNotEquals(null,updateConfidenceService.updateConfidence(feedback));
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
    }

}