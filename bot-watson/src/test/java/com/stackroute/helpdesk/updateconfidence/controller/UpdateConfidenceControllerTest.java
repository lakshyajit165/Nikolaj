package com.stackroute.helpdesk.updateconfidence.controller;


import com.stackroute.helpdesk.updateconfidence.model.Feedback;
import com.stackroute.helpdesk.updateconfidence.updateconfidenceservice.UpdateConfidenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateConfidenceControllerTest {

    @Autowired
    UpdateConfidenceController updateConfidenceController;

    @MockBean
    UpdateConfidenceService updateConfidenceService;

    @Test
    public void updateConfidence() throws Exception {
        Feedback feedback=new Feedback("intentName","commandName",5);
        when(updateConfidenceService.updateConfidence(feedback)).thenReturn("confidence for intent and command updated sucessfully");
        assertEquals("confidence for intent and command updated sucessfully",updateConfidenceController.updateConfidence(feedback).getBody().get("result"));
        assertNotEquals("something",updateConfidenceController.updateConfidence(feedback).getBody().get("result"));
    }
}
//package com.stackroute.helpdesk.updateconfidence.controller;
//
//
//import com.stackroute.helpdesk.updateconfidence.updateconfidenceservice.UpdateConfidenceService;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UpdateConfidenceControllerTest {
//
//    @Autowired
//    UpdateConfidenceController updateConfidenceController;
//
//    @MockBean
//    UpdateConfidenceService updateConfidenceService;
//
////    @org.junit.Test
////    public void updateConfidence() throws Exception {
////        when(updateConfidenceService.updateConfidence("intentName","commandName","relationshipName",5)).thenReturn("confidence for intent and command updated sucessfully");
////        assertEquals("confidence for intent and command updated sucessfully",updateConfidenceController.updateConfidence("intentName","commandName","relationshipName",5).getBody().get("result"));
////    }
