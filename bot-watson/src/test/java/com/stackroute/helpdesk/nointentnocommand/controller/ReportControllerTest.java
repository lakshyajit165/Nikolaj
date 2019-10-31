package com.stackroute.helpdesk.nointentnocommand.controller;

import com.stackroute.helpdesk.nointentnocommand.model.Report;
import com.stackroute.helpdesk.nointentnocommand.model.ReportType;
import com.stackroute.helpdesk.nointentnocommand.model.UpdateReport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportControllerTest {

    @Autowired
    ReportController reportController;

    @MockBean
    ReportService reportService;

    List<Report> list;
    Report report;

    @Before
    public void setUp(){
        list=new ArrayList<Report>();
    }

    @Test
    public void updateReport() {
        String result="updated sucessfully";
        UpdateReport updateReport=new UpdateReport("ticketId","entity","intent","command");
        when(reportService.updateReport(updateReport)).thenReturn(result);
        assertEquals(result,reportController.updateReport(updateReport).getBody().get("result"));
        assertNotEquals(null,reportController.updateReport(updateReport).getBody().get("result"));
    }

    @Test
    public void addingNoCommandRecord() {
        Date date=new Date();
        report=new Report("ticketid","query","intent",date,date,"abc@gmail.com","hii", ReportType.NoCommand,null);
        list.add(report);
        when(reportService.addRecord(report)).thenReturn(list);
        assertEquals(list,reportController.addRecord(report).getBody().get("result"));
    }
    @Test
    public void addingNoIntentRecord() {
        Date date = new Date();
        report=new Report("ticketid","query",null,date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        list.add(report);
        when(reportService.addRecord(report)).thenReturn(list);
        assertEquals(list,reportController.addRecord(report).getBody().get("result"));
    }
}