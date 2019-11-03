package com.stackroute.helpdesk.commandregistry.trackissueservice.service;


import com.google.gson.internal.LinkedTreeMap;
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
            jsonObject.put("data1",jsonObj.get(report));
//            String keyvalue = (String) jsonObj.get(report);
//            JSONObject jsonObject= (JSONObject) jsonObj.get(keyStr);
            if(!(jsonObj.get(report)=="Well done by developers.Currently all quries have commands") || !(jsonObj.get(report)=="Well done by developers.Currently all quries have Intents")){
//                printJsonObject2((JSONObject) jsonObj.get(report));
                System.out.println("inside if for printJsonObject2 function");
                printJsonObject2(jsonObject);
            }
            else{
                System.out.println("report if condition");
            }
        }
    }
    public void printJsonObject2(JSONObject jsonObj) {
        System.out.println("jsonObject.get data class name = " + jsonObj.get("data1").getClass() + " data1 = " + jsonObj.get("data1"));
//        JSONObject jsonObject= (JSONObject) jsonObj.get("data1");
        LinkedTreeMap jsonObject = (LinkedTreeMap) jsonObj.get("data1");
        for (Object key : jsonObject.keySet()) {
            System.out.println(jsonObject);
            //based on you key types
            entity = (String)key;
            System.out.println();
//            Object keyvalue = jsonObj.get(keyStr);
            printJsonObject3((LinkedTreeMap) jsonObject.get(entity));

        }
    }
    public void printJsonObject3(LinkedTreeMap jsonObj) {
        System.out.println("report outside condition3");
        for (Object key : jsonObj.keySet()) {
            //based on you key types
            intent = (String)key;
            System.out.println("class" + jsonObj.get(intent).getClass());
            System.out.println("dfghj"+(jsonObj.get(intent)));
            List<Intent> keyvalue = (List<Intent>) jsonObj.get(intent);

            ReportDetails reportDetails=new ReportDetails(report,entity,intent,keyvalue);
            reportRepository.save(reportDetails);

        }
    }

}
