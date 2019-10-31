package com.stackroute.helpdesk.commandregistry.trackissueservice.service;

import com.stackroute.helpdesk.commandregistry.trackissueservice.entity.ReportDetails;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;


@Service
public class NoCommandReport {
    public JSONObject formattingReportIntent(String type, List<ReportDetails> reportDetails) {
        HashMap<String, Object> data3 = new HashMap<>();
        HashMap<String,Object> data4 = new HashMap<>();
        HashMap<String, Object> noCommand = new HashMap<>();
        HashMap<String, Object> commandMapped = new HashMap<>();
        HashMap<String,Object> commandReport = new HashMap<>();
        HashMap<String, Object> queries = new HashMap<>();
        HashSet<String> setOfEntities = new HashSet();
        List<HashMap<String, Object>> entitiesList = new ArrayList<>();
        List<HashMap<String, Object>> commandList = new ArrayList<>();
        List<HashMap<String, Object>> reportList = new ArrayList<>();

        reportDetails.stream().forEach(entities -> {
            if(isNullOrEmpty(entities.getEntity()))
                setOfEntities.add("null");
            else
                setOfEntities.add(entities.getEntity());
        });

        setOfEntities.forEach(uniqueEntity -> {
            HashMap<String, Object> entity = new HashMap<>();
            List<HashMap<String, Object>> intentsList = new ArrayList<>();
            HashSet<String> setOfIntents = new HashSet();
            reportDetails.stream().forEach(entities -> {
                if(uniqueEntity == entities.getEntity()) {

                    entities.getIntentList().stream().forEach(intent -> {
                        setOfIntents.add(intent.getIntent());
                    });

                    setOfIntents.forEach(uniqueIntent -> {
                        HashMap<String, Object> intents = new HashMap<>();
                        List<HashMap<String, Object>> queriesList = new ArrayList<>();
                        System.out.println("unique intent = " + uniqueIntent);
                        entities.getIntentList().stream().forEach(intent -> {
                            System.out.println("current intent = " + intent.getIntent() + " but unique intent = " + uniqueIntent);
                            if (uniqueIntent == intent.getIntent()) {
                                queries.put("name", intent.getTicketName());
                                queries.put("size", 300);
                                queriesList.add(queries);
                            }
                        });
                        intents.put("children", queriesList);
                        intents.put("name", uniqueIntent);
                        intentsList.add(intents);
                    });
                }
            });
            entity.put("children", intentsList);
            entity.put("name", uniqueEntity);
            entitiesList.add(entity);
        });
        noCommand.put("children", entitiesList);
        noCommand.put("name", "noCommand");
        commandMapped.put("name", "commandMapped");
        commandMapped.put("children",entitiesList);
        reportList.add(noCommand);
        reportList.add(commandMapped);

        data3.put("children", reportList);
        data3.put("name","commandReport");
//        commandList.add(data3);
//        data4.put("name","commandReport");
//        data4.put("children",commandList);
        return new JSONObject(data3);
    }
}
