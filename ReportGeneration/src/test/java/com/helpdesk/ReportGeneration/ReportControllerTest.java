//package com.helpdesk.ReportGeneration;
//
//import com.helpdesk.ReportGeneration.Repository.ReportDao;
//import com.helpdesk.ReportGeneration.controller.ReportController;
//import com.helpdesk.ReportGeneration.entity.Report;
//import com.helpdesk.ReportGeneration.entity.ticketStatus;
//import com.helpdesk.ReportGeneration.entity.type;
//import com.helpdesk.ReportGeneration.service.ReportInterface;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ReportControllerTest {
//
//    @Autowired
//    private ReportController reportController;
//
//    @Mock
//    private ReportDao reportDao;
//
//    @Mock
//    private ReportInterface reportInterface;
//
//    @Autowired
//    ReportDao reportDaoTest;
//
//    String tags1[] = {"invoice"};
//    String tags2[] = {"credit", "hacked"};
//
//    type type1 = type.query;
//    type type2 = type.grievance;
//
//    UUID uuid1 = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");
//    UUID uuid2 = UUID.fromString("d81d4e2e-bcf2-11e6-869b-7df92533d2db");
//
//    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus1 = com.helpdesk.ReportGeneration.entity.ticketStatus.open;
//    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus2 = ticketStatus.open;
//
//    Report report1 = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
//    Report report2 = new Report(uuid1,uuid1, "my credit card hacked", "usermail2.com", type2, new Date(), ticketStatus2, "csr2.com", new Date(), uuid2, new Date(), "delivery guy", new Date(), tags2, 3, "csr2.com", new Date());
//
//    @BeforeEach
//    public void setUp() {
//        reportDaoTest.deleteAll();
//        reportDaoTest.save(report1);
//        reportDaoTest.save(report2);
//        System.out.println("here in the embedded mongodb is " + reportDaoTest.findAll());
//    }
//
//
//    @Test
//    public void saveReportsTest() throws Exception {
//        assertEquals(reportController.addReport(report1).getStatusCode(),HttpStatus.OK);
//    }
//
//    @Test
//    public void saveReportsTestPass() throws Exception {
//        assertEquals(reportController.addReport(report1).getStatusCodeValue(),200);
//    }
//
//    @Test
//    public void saveReportsTestPass1() throws Exception {
//        assertNotEquals(reportController.addReport(report1).getBody(),report1);
//    }
//
//    @Test
//    public void getServiceReportTest() throws Exception {
//        assertEquals(reportController.getServiceReport("2019-10-04").getStatusCode(),HttpStatus.OK);
//    }
//
//
//    @Test
//    public void getallReportTest() throws Exception {
//        assertEquals(reportController.getReportsByStatus("",1,10).getStatusCode(),HttpStatus.OK);
//    }
//
//    @Test
//    public void getOpenReportTest() throws Exception {
//        assertEquals(reportController.getReportsByStatus("open",1,10).getStatusCode(),HttpStatus.OK);
//    }
//
//    @Test
//    public void getClosedReportTest() throws Exception {
//        assertEquals(reportController.getReportsByStatus("closed",1,10).getStatusCode(),HttpStatus.OK);
//    }
//
//    @Test
//    public void getEngagedReportTest1() throws Exception {
//        assertEquals(reportController.getReportsByStatus("engaged",1,10).getStatusCode(),HttpStatus.OK);
//    }
//
//
//}
