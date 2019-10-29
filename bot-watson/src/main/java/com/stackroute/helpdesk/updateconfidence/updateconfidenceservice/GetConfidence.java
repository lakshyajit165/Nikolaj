package com.stackroute.helpdesk.updateconfidence.updateconfidenceservice;

import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jService;
import com.stackroute.helpdesk.updateconfidence.exceptionclass.NoMatchFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetConfidence {

    @Autowired
    Neo4jService neo4jService;

    public Integer getConfidence(String intentName, String commandName, String relationshipName) {
        Integer confidence = 0;
        Map map;

            try {
                List list= neo4jService.getConfidence(intentName,commandName);
                map = (Map) list.get(0);
                System.out.println(list);
            }
            catch (IndexOutOfBoundsException e){
                throw new NoMatchFound("either intent,command and relationship match not found");
            }
        Long originalConfidence = (Long) map.get("e.confidence");
            confidence=originalConfidence.intValue();
        System.out.println(confidence);
        return confidence;
    }
}
