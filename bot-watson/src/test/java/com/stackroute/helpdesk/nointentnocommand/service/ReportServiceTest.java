package com.stackroute.helpdesk.nointentnocommand.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.matcher.Matchers;
import com.netflix.discovery.converters.Auto;
import com.stackroute.helpdesk.nointentnocommand.messaging.MessageSender;
import com.stackroute.helpdesk.nointentnocommand.model.Report;
import com.stackroute.helpdesk.nointentnocommand.model.ReportType;
import com.stackroute.helpdesk.nointentnocommand.model.UpdateReport;
import com.stackroute.helpdesk.nointentnocommand.repository.ReportRepository;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @MockBean
    ReportRepository reportRepository;

//    @MockBean
//    MessageSender messageSender;

    MessageSender messageSender;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Environment environment;

    List<Report> list;
    Report report;
    JSONObject jsonObject;
    JSONObject clusteredData;


    @Before
    public void setUp(){
        list=new ArrayList<Report>();
        jsonObject=new JSONObject();
        clusteredData=new JSONObject();
        messageSender=mock(MessageSender.class);
    }
    @Test(expected = AmqpConnectException.class)
    public void getReportWithEmptyData() throws JsonProcessingException {
        jsonObject.put("NoCommand", "Well done by developers.Currently all quries have commands");
        jsonObject.put("NoIntent", "Well done by developers.Currently all quries have Intents");
        when(reportRepository.findAll()).thenReturn(list);
        assertEquals(jsonObject,reportService.getReport());
    }
    @Test(expected = AmqpConnectException.class)
    public void getReportForNoCommand() throws JsonProcessingException {
        Date date=new Date();
        JSONObject clusteredData=new JSONObject();
        this.report=new Report("ticketid","query","intent",date,date,"abc@gmail.com","entity", ReportType.NoCommand,null);
        jsonObject.put("intent",report);
        clusteredData.put("entity",jsonObject);
        jsonObject.clear();
        jsonObject.put("NoCommand",clusteredData);
        jsonObject.put("NoIntent", "Well done by developers.Currently all quries have Intents");
        list.add(report);
        when(reportRepository.findAll()).thenReturn(list);
//        when(messageSender.sendMessage(rabbitTemplate,environment.getProperty("optimus.exchange.name"),environment.getProperty("optimus.nointentreport.generated.routing.key"),jsonObject));
//        Mockito.doNothing().when(messageSender).sendMessage(rabbitTemplate,environment.getProperty("optimus_exchange"),environment.getProperty("optimus.nointentreport.generated"),jsonObject);
        assertEquals(jsonObject,reportService.getReport());
    }

    @Test(expected = AmqpConnectException.class)
    public void getReportForNoIntentBasedOnDataFromCsr() throws JsonProcessingException {
        Date date=new Date();
        this.report=new Report("ticketid","query","intent",date,date,"abc@gmail.com","entity", ReportType.NoIntent,"command");
        jsonObject.put("intent",report);
        clusteredData.put("entity",jsonObject);
        jsonObject.clear();
        jsonObject.put("NoIntent",clusteredData);
        jsonObject.put("NoCommand", "Well done by developers.Currently all quries have commands");
        when(reportRepository.findAll()).thenReturn(list);
        assertEquals(jsonObject,reportService.getReport());
    }

    @Test(expected = AmqpConnectException.class)
    public void getReportForNoIntent() throws JsonProcessingException {
        Date date = new Date();
        this.report=new Report("ticketid","query",null,date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        jsonObject.put("Unknown_Intent",report);
        clusteredData.put("Unknown_Entity",jsonObject);
        jsonObject.clear();
        jsonObject.put("NoIntent",clusteredData);
        jsonObject.put("NoCommand", "Well done by developers.Currently all quries have commands");
        when(reportRepository.findAll()).thenReturn(list);
        assertEquals(jsonObject,reportService.getReport());
    }

    @Test(expected = AmqpConnectException.class)
    public void getNoIntentAndNoCommand() throws JsonProcessingException {
        Date date=new Date();
        this.report=new Report("ticketid","query",null,date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        list.add(report);
        this.report=new Report("ticketid","query","intent",date,date,"abc@gmail.com","entity", ReportType.NoCommand,null);
        list.add(report);
        jsonObject.put("Unknown_Intent",report);
        clusteredData.put("Unknown_Entity",jsonObject);
        jsonObject.clear();
        jsonObject.put("NoIntent",clusteredData);
        clusteredData.clear();
        clusteredData.put("intent",report);
        clusteredData.put("entity",clusteredData.get("intent"));
        jsonObject.put("NoCommand",clusteredData);
        when(reportRepository.findAll()).thenReturn(list);
        assertEquals(jsonObject,reportService.getReport());
    }

    @Test
    public void intentClusteringForUnknownIntent() {
        Date date = new Date();
        this.report=new Report("ticketid","query",null,date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        list.add(report);
        jsonObject.put("Unknown_Intent",list);
        assertEquals(jsonObject,reportService.intentClustering(list));
    }

    @Test
    public void intentClusteringForKnownIntent() {
        Date date = new Date();
        this.report=new Report("ticketid","query","intent",date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        list.add(report);
        jsonObject.put("intent",list);
        assertEquals(jsonObject,reportService.intentClustering(list));
    }
    @Test
    public void intentClusteringFromQuery() {
        Date date = new Date();
        this.report=new Report("ticketid","i want to book a ride",null,date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        list.add(report);
        jsonObject.put("book",list);
        assertEquals(jsonObject,reportService.intentClustering(list));
    }

    @Test
    public void findingIntents() {
        List<String> intents=new ArrayList<>();
        intents.add("book");
        assertEquals(intents,reportService.findingIntents("i want to book a ride"));
    }

    @Test
    public void findingIntentsWithNoIntentsText() {
        List<String> intents=new ArrayList<>();
        assertEquals(intents,reportService.findingIntents("ride"));
    }

    @Test
    public void updateReport() {
        String result="updated sucessfully";
        Date date = new Date();
        this.report=new Report("ticketid","query",null,date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        UpdateReport updateReport=new UpdateReport("ticketId","entity","intent","command");
        list.add(report);
        when(reportRepository.findById(updateReport.getTicketId())).thenReturn(Optional.ofNullable(report));
        report.setEntity(updateReport.getEntity());
        report.setIntent(updateReport.getIntent());
        report.setCommandName(updateReport.getCommand());
        when(reportRepository.save(report)).thenReturn(report);
        assertEquals(result,reportService.updateReport(updateReport));
        assertNotEquals(null,reportService.updateReport(updateReport));
    }

    @Test
    public void addRecord() {
        Date date = new Date();
        this.report=new Report("ticketid","query",null,date,date,"abc@gmail.com",null, ReportType.NoIntent,null);
        list.add(report);
        when(reportRepository.save(report)).thenReturn(report);
        assertEquals(list,reportService.addRecord(report));
    }
}