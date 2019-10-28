package com.stackroute.helpdesk.nointentnocommand.service;

import com.stackroute.helpdesk.nointentnocommand.model.Report;
import com.stackroute.helpdesk.nointentnocommand.model.ReportType;
import com.stackroute.helpdesk.nointentnocommand.model.UpdateReport;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportServiceRepo {
    JSONObject getReport();
    List addRecord(Report report);
    public String updateReport(UpdateReport updateReport);
//    JSONObject schedulingReports();
    Map<String,List<Report>> intentClustering(List<Report> reports);
}
