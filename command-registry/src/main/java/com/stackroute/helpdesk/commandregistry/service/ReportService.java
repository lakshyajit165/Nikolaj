package com.stackroute.helpdesk.commandregistry.service;


import com.stackroute.helpdesk.commandregistry.commandstorage.model.ReportDetails;
import com.stackroute.helpdesk.commandregistry.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
}
