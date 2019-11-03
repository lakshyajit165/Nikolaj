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

        HashSet<String> setOfEntities = new HashSet();
        List<HashMap<String, Object>> entitiesList = new ArrayList<>();
        List<HashMap<String, Object>> reportList = new ArrayList<>();

        List<HashMap<String, Object>> mappedEntitiesList = new ArrayList<>();
        List<HashMap<String, Object>> mappedReportList = new ArrayList<>();


        reportDetails.stream().forEach(entities -> {
            if(isNullOrEmpty(entities.getEntity()))
                setOfEntities.add("null");
            else
                setOfEntities.add(entities.getEntity());
        });

        setOfEntities.forEach(uniqueEntity -> {
            HashMap<String, Object> entity = new HashMap<>();
            HashMap<String, Object> mappedEntity = new HashMap<>();
            List<HashMap<String, Object>> intentsList = new ArrayList<>();
            List<HashMap<String, Object>> mappedIntentsList = new ArrayList<>();
            HashSet<String> setOfIntents = new HashSet();
            reportDetails.stream().forEach(entities -> {
                if(uniqueEntity == entities.getEntity()) {

                    entities.getIntentList().stream().forEach(intent -> {
                        setOfIntents.add(intent.getIntent());
                    });

                    setOfIntents.forEach(uniqueIntent -> {
                        HashMap<String, Object> intents = new HashMap<>();
                        HashMap<String, Object> mappedIntents = new HashMap<>();
                        List<HashMap<String, Object>> queriesList = new ArrayList<>();
                        List<HashMap<String, Object>> mappedIntentQueriesList = new ArrayList<>();
                        System.out.println("unique intent = " + uniqueIntent);
                        entities.getIntentList().stream().forEach(intent -> {
                            System.out.println("current intent = " + intent.getIntent() + " but unique intent = " + uniqueIntent);
                            if (uniqueIntent == intent.getIntent()) {
                                HashMap<String, Object> queries = new HashMap<>();
                                System.out.println("command name = " + intent.getCommandName());
                                if(intent.getCommandName().equalsIgnoreCase("")) {
                                    queries.put("name", intent.getTicketName());
                                    queries.put("size", 300);
                                    queriesList.add(queries);
                                System.out.println("queries = "+ queries.get("name"));
                                }
                                else {
                                    queries.put("name", intent.getTicketName());
                                    queries.put("size", 300);
                                    mappedIntentQueriesList.add(queries);
                                }
                            }
                            System.out.println("queries list = " + queriesList);
                        });
                        intents.put("children", queriesList);
                        intents.put("name", uniqueIntent);
                        mappedIntents.put("children", mappedIntentQueriesList);
                        mappedIntents.put("name", uniqueIntent);
                        intentsList.add(intents);
                        mappedIntentsList.add(mappedIntents);
                    });
                }
            });
            entity.put("children", intentsList);
            entity.put("name", uniqueEntity);
            entitiesList.add(entity);

            mappedEntity.put("children", mappedIntentsList);
            mappedEntity.put("name", uniqueEntity);
            mappedEntitiesList.add(mappedEntity);
        });
        noCommand.put("children", entitiesList);
        noCommand.put("name", "noCommand");
        commandMapped.put("name", "commandMapped");
        commandMapped.put("children",mappedEntitiesList);
        reportList.add(noCommand);
        reportList.add(commandMapped);

        data3.put("children", reportList);
        data3.put("name","commandReport");
//        commandList.add(data3);
//        data3.put("name","commandReport");
//        data3.put("children",commandList);
        return new JSONObject(data3);
    }
}
