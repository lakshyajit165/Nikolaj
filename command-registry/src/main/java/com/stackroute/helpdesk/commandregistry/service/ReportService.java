package com.stackroute.helpdesk.commandregistry.service;
import com.stackroute.helpdesk.commandregistry.entity.Intent;
import com.stackroute.helpdesk.commandregistry.entity.ReportDetails;
import com.stackroute.helpdesk.commandregistry.repository.ReportRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ReportService implements ReportInterface {
    @Autowired
    private ReportRepository reportRepository;

    String report;
    String entity;
    String intent;

    @Override
    public List<ReportDetails> getReportsByType(String type) {
        return reportRepository.findAll();
    }

    public void printJsonObject1(JSONObject jsonObj) {
        for (Object key : jsonObj.keySet()) {
            report = (String)key;
//            String keyvalue = (String) jsonObj.get(report);
//            JSONObject jsonObject= (JSONObject) jsonObj.get(keyStr);
            printJsonObject2((JSONObject) jsonObj.get(report));
        }
    }
    public void printJsonObject2(JSONObject jsonObj) {
        for (Object key : jsonObj.keySet()) {
            //based on you key types
            entity = (String)key;
//            Object keyvalue = jsonObj.get(keyStr);
            printJsonObject3((JSONObject) jsonObj.get(entity));
        }
    }
    public void printJsonObject3(JSONObject jsonObj) {
        for (Object key : jsonObj.keySet()) {
            //based on you key types
            intent = (String)key;
            List<Intent> keyvalue = (List<Intent>) jsonObj.get(intent);
            ReportDetails reportDetails=new ReportDetails(report,entity,intent,keyvalue);
            reportRepository.save(reportDetails);
        }
    }
}