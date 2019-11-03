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
public class NoIntentReport {
    public JSONObject formattingReportIntent(String type, List<ReportDetails> reportDetails) {
        HashMap<String, Object> data3 = new HashMap<>();
        HashMap<String, Object> noIntent = new HashMap<>();
        HashMap<String, Object> intentMapped = new HashMap<>();
        HashSet<String> setOfEntities = new HashSet();
        HashSet<String> mappedSetOfEntities = new HashSet();
        List<HashMap<String, Object>> reportList = new ArrayList<>();
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
                        if(isNullOrEmpty(intent.getIntent())){
                            setOfIntents.add("null");
                        }
                        else {
                            setOfIntents.add(intent.getIntent());
                        }
                    });

                    setOfIntents.forEach(uniqueIntent -> {
                        HashMap<String, Object> intents = new HashMap<>();
                        HashMap<String, Object> mappedIntents = new HashMap<>();
                        List<HashMap<String, Object>> queriesList = new ArrayList<>();
                        List<HashMap<String, Object>> mappedQueriesList = new ArrayList<>();

                        System.out.println("unique intent = " + uniqueIntent);
                        entities.getIntentList().stream().forEach(intent -> {
                            System.out.println("current intent = " + intent.getIntent() + " but unique intent = " + uniqueIntent);
                            if (uniqueIntent == intent.getIntent()) {
                                HashMap<String, Object> queries = new HashMap<>();
                                if(intent.getCommandName().equalsIgnoreCase("")) {
                                    queries.put("name", intent.getTicketName());
                                    queries.put("size", 300);
                                    queriesList.add(queries);
                                }
                                else {
                                    queries.put("name", intent.getTicketName());
                                    queries.put("size", 300);
                                    mappedQueriesList.add(queries);
                                }
                            }
                        });
                        noIntent.put("children", queriesList);
                        noIntent.put("name", "noIntent");
                        intentMapped.put("name", "intentMapped");
                        intentMapped.put("children", mappedQueriesList);
                    });
                }
            });
            reportList.add(noIntent);
            reportList.add(intentMapped);
        });
//        System.out.println("queries list = " + queriesList);
//        noIntent.put("children", queriesList);
//        noIntent.put("name", "noIntent");


//        reportList.add(intentMapped);
        data3.put("children", reportList);
        data3.put("name", "intentReport");
        return new JSONObject(data3);
    }
}
