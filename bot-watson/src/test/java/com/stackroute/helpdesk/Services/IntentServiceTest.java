package com.stackroute.helpdesk.Services;

import com.stackroute.helpdesk.makeintententities.Services.IntentService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntentServiceTest {
    //@Autowired
    IntentService intentService=new IntentService() ;
    @Test
    public void testIntentCreate(){
        List<String> testExamples=new ArrayList<>();
        testExamples.add("aaaa");
        testExamples.add("bbbb");
        String actual=intentService.createIntent("intent1",testExamples);
        String expected="intent1";
        assertEquals(actual,expected);

    }
}