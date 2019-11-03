package com.stackroute.helpdesk.commandregistry.trackissueservice.service;


import com.stackroute.helpdesk.commandregistry.trackissueservice.entity.Intent;
import com.stackroute.helpdesk.commandregistry.trackissueservice.entity.ReportDetails;
import com.stackroute.helpdesk.commandregistry.trackissueservice.repository.ReportRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService implements ReportInterface {

    @Autowired
    private ReportRepository reportRepository;


    @Override
    public List<ReportDetails> getReportsByType(String type) {
        return reportRepository.findAll();
    }





    String report;
    String entity;
    String intent;
    public void printJsonObject1(JSONObject jsonObj) {
        System.out.println("report outside condition");
        System.out.println("data1"+jsonObj);
        for (Object key : jsonObj.keySet()) {
            report = (String)key;
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("data1",jsonObj);
//            String keyvalue = (String) jsonObj.get(report);
//            JSONObject jsonObject= (JSONObject) jsonObj.get(keyStr);
            if(!(jsonObj.get(report)=="Well done by developers.Currently all quries have commands") || !(jsonObj.get(report)=="Well done by developers.Currently all quries have Intents")){
//                printJsonObject2((JSONObject) jsonObj.get(report));
                System.out.println("report if condition");
            }
            else{
                printJsonObject2(jsonObject);
            }
        }
    }
    public void printJsonObject2(JSONObject jsonObj) {
        System.out.println("report outside condition2");
        for (Object key : jsonObj.keySet()) {
            //based on you key types
            entity = (String)key;
//            Object keyvalue = jsonObj.get(keyStr);
            printJsonObject3((JSONObject) jsonObj.get(entity));

        }
    }
    public void printJsonObject3(JSONObject jsonObj) {
        System.out.println("report outside condition3");
        for (Object key : jsonObj.keySet()) {
            //based on you key types
            intent = (String)key;
            List<Intent> keyvalue = (List<Intent>) jsonObj.get(intent);

            ReportDetails reportDetails=new ReportDetails(report,entity,intent,keyvalue);
            reportRepository.save(reportDetails);

        }
    }

}
