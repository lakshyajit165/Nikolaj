package com.helpdesk.ReportGeneration;

import com.helpdesk.ReportGeneration.Repository.ReportDao;
import com.helpdesk.ReportGeneration.entity.Report;
import com.helpdesk.ReportGeneration.entity.Service;
import com.helpdesk.ReportGeneration.entity.ticketStatus;
import com.helpdesk.ReportGeneration.entity.type;
import com.helpdesk.ReportGeneration.service.ReportInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceTest {

    @Mock
    ReportDao reportDao;

    @Autowired
    ReportInterface reportInterface;

    @Autowired
    ReportDao reportDaoTest;

    String tags1[] = {"invoice"};
    String tags2[] = {"credit", "hacked"};

    type type1 = type.query;
    type type2 = type.grievance;

    String uuid1 = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db").toString();
    String uuid2 = UUID.fromString("d81d4e2e-bcf2-11e6-869b-7df92533d2db").toString();

    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus1 = com.helpdesk.ReportGeneration.entity.ticketStatus.open;
    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus2 = ticketStatus.open;

    Report report1 = new Report(uuid1, uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
    Report report2 = new Report(uuid1, uuid1, "my credit card hacked", "usermail2.com", type2, new Date(), ticketStatus2, "csr2.com", new Date(), uuid2, new Date(), "delivery guy", new Date(), tags2, 3, "csr2.com", new Date());


    @Before
    public void setUp() {
        reportDaoTest.deleteAll();
        reportDaoTest.save(report1);
        reportDaoTest.save(report2);
        System.out.println("here in the embedded mongodb is " + reportDaoTest.findAll());
    }

    @Test
    public void getReportsTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAll(), reportInterface.getReports(1, 10));
    }

    @Test
    public void getReportByStatusOpenTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAllByTicketStatus("open"), reportInterface.getReportsByStatus("open", 1, 10));
    }

    //no report that is closed
    @Test
    public void getReportByStatusClosedTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAllByTicketStatus("closed"), reportInterface.getReportsByStatus("closed", 1, 10));
    }

    //no report that is engaged
    @Test
    public void getReportByStatusEngagedTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAllByTicketStatus("engaged"), reportInterface.getReportsByStatus("engaged", 1, 10));
    }


    @Test
    public void getAllReportsTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAll(), reportInterface.getReportsByStatus("", 1, 10));
    }

    @Test
    public void getOpenReportsTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAllByTicketStatus("open"), reportInterface.getReportsByStatus("open", 1, 10));
    }

    @Test
    public void getClosedReportsTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAllByTicketStatus("closed"), reportInterface.getReportsByStatus("closed", 1, 10));
    }

    @Test
    public void getEngagedReportsTest() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(reportDao.findAllByTicketStatus("engaged"), reportInterface.getReportsByStatus("engaged", 1, 10));
    }

    @Test
    public void getReportByIdPass() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertEquals(report2.getEntity(), reportInterface.getReportById(uuid1).getEntity());
    }

    //no such id
    @Test
    public void getReportByIdFail() throws Exception {
        reportDao.save(report1);
        reportDao.save(report2);
        assertNotEquals(report1, reportInterface.getReportById(uuid1));
    }

    @Test
    public void getDataForService() throws ParseException {
        reportDao.save(report1);
        reportDao.save(report2);
        Service service = new Service("delivery guy",1,0,0);
        assertEquals(service.getEntity(), reportInterface.getDataforService().get(0).getEntity());
    }

    @Test
    public void getDataForServiceByDate() throws ParseException {
        reportDao.save(report1);
        reportDao.save(report2);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String todate = dateFormat.format(date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date todate1 = cal.getTime();
        String fromdate = dateFormat.format(todate1);
        Date date1 = new Date();
        String toDate = dateFormat.format(date1);
        Service service = new Service();
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service);
        assertEquals(serviceList.get(0).getLeadTime(), reportInterface.getDataforService().get(0).getLeadTime());
    }


    @Test
    public void getSizeReportsByStatus()
    {
        reportDao.save(report1);
        reportDao.save(report2);
        List<Report> list = new ArrayList<>();
        list.add(report1);
        Integer size = list.size();
        assertEquals(size, reportInterface.getReportsByStatus(""));

    }

    @Test
    public void getOpenReportsSize()
    {
        reportDao.save(report1);
        reportDao.save(report2);
        List<Report> list = new ArrayList<>();
        list.add(report1);
        Integer size = list.size();
        assertEquals(size, reportInterface.getReportsByStatus("open"));

    }

    @Test
    public void getClosedReportsSize()
    {
        reportDao.save(report1);
        reportDao.save(report2);
        List<Report> list = new ArrayList<>();
        list.add(report1);
        Integer size = 0; //no closed reports
        assertEquals(size, reportInterface.getReportsByStatus("closed"));

    }

    @Test
    public void getEngagedReportsSize()
    {
        reportDao.save(report1);
        reportDao.save(report2);
        List<Report> list = new ArrayList<>();
        list.add(report1);
        Integer size = 0;  //no engaged reports
        assertEquals(size, reportInterface.getReportsByStatus("engaged"));

    }



}
