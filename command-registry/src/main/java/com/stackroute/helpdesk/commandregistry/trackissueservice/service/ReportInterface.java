package com.stackroute.helpdesk.commandregistry.trackissueservice.service;

import com.stackroute.helpdesk.commandregistry.trackissueservice.entity.ReportDetails;

import java.util.List;

public interface ReportInterface {

     List<ReportDetails> getReportsByType(String type) ;

}
