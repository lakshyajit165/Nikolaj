package com.stackroute.helpdesk.commandregistry.service;
import com.stackroute.helpdesk.commandregistry.commandstorage.model.ReportDetails;
import java.util.List;

public interface ReportInterface {

     List<ReportDetails> getReportsByType(String type) ;

}
