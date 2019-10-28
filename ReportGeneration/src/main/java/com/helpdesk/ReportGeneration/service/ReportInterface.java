package com.helpdesk.ReportGeneration.service;

import com.helpdesk.ReportGeneration.entity.Report;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public interface ReportInterface {

    public List<Report> getReports(int page, int limit);

    public void saveReport(Report report);

    public Report getReportById(String id);

    public List<com.helpdesk.ReportGeneration.entity.Service> getDataforService(String date, String end) throws ParseException;

    public List<com.helpdesk.ReportGeneration.entity.Service> getDataforService()  throws ParseException;

    public List<Report> getReportsByStatus(String status, int page, int limit);

    public List<Report> getReportsByStatus(int page, int limit);

    public Integer getReportsByStatus(String status);

    public JSONObject getAllQuery(Date startdate, Date endDate);

    public JSONObject getAvgRating(Date startdate, Date endDate);

    public JSONObject getTicketsReopen(Date startdate, Date endDate);


}
