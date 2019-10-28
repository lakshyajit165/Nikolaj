package com.helpdesk.ReportGeneration;

import com.helpdesk.ReportGeneration.controller.ReportController;
import com.helpdesk.ReportGeneration.entity.Report;
import com.helpdesk.ReportGeneration.entity.Service;
import com.helpdesk.ReportGeneration.entity.ticketStatus;
import com.helpdesk.ReportGeneration.entity.type;
import com.helpdesk.ReportGeneration.service.CsrReliabilityInterface;
import com.helpdesk.ReportGeneration.service.ReportInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportInterface reportInterface;

    @MockBean
    private CsrReliabilityInterface csrReliabilityInterface;


    String tags1[] = {"invoice"};
    String tags2[] = {"credit", "hacked"};

    type type1 = type.query;
    type type2 = type.grievance;

    String uuid1 = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db").toString();
    String uuid2 = UUID.fromString("d81d4e2e-bcf2-11e6-869b-7df92533d2db").toString();

    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus1 = com.helpdesk.ReportGeneration.entity.ticketStatus.open;
    com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus2 = ticketStatus.open;

    Report report1 = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
    Report report2 = new Report(uuid1,uuid1, "my credit card hacked", "usermail2.com", type2, new Date(), ticketStatus2, "csr2.com", new Date(), uuid2, new Date(), "delivery guy", new Date(), tags2, 3, "csr2.com", new Date());

    @Test
    public void getServiceReportTest() throws Exception {
        Service service = new Service("resturant",1,1,2);
        List<Service> serviceReport = new ArrayList<>();
        serviceReport.add(service);
        BDDMockito.given(reportInterface.getDataforService()).willReturn(serviceReport);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/report/service")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getServiceReportByNoDateTest() throws Exception {
        Service service = new Service("resturant",1,1,2);
        List<Service> serviceReport = new ArrayList<>();
        serviceReport.add(service);
        BDDMockito.given(reportInterface.getDataforService()).willReturn(serviceReport);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/report/service?startdate=&enddate=")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getServiceReportByDateTest() throws Exception {
        String startDate = new Date().toString();
        String endDate = new Date().toString();
        Service service = new Service("resturant",1,1,2);
        List<Service> serviceReport = new ArrayList<>();
        serviceReport.add(service);
        String url = "/api/v1/report/service?startdate="+startDate+"&enddate="+endDate;
        BDDMockito.given(reportInterface.getDataforService(startDate,endDate)).willReturn(serviceReport);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllReports() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        BDDMockito.given(reportInterface.getReportsByStatus("",1,10)).willReturn(reports);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reports")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOpenReports() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        String url = "/api/v1/reports?status=open&page=1&limit=10";
        BDDMockito.given(reportInterface.getReportsByStatus("open",1,10)).willReturn(reports);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getClosedReports() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        String url = "/api/v1/reports?status=closed&page=1&limit=10";
        BDDMockito.given(reportInterface.getReportsByStatus("closed",1,10)).willReturn(reports);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getEngagedReports() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        String url = "/api/v1/reports?status=engaged&page=1&limit=10";
        BDDMockito.given(reportInterface.getReportsByStatus("engaged",1,10)).willReturn(reports);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllReportsSize() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        int size = 1;
        String url = "/api/v1/reportsize?status=";
        BDDMockito.given(reportInterface.getReportsByStatus("")).willReturn(size);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getOpenReportsSize() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        int size = 1;
        String url = "/api/v1/reportsize?status=open";
        BDDMockito.given(reportInterface.getReportsByStatus("open")).willReturn(size);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getClosedReportsSize() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        int size = 1;
        String url = "/api/v1/reportsize?status=closed";
        BDDMockito.given(reportInterface.getReportsByStatus("closed")).willReturn(size);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getEngagedReportsSize() throws Exception {
        List<Report> reports = new ArrayList<>();
        Report report = new Report(uuid1,uuid1, "bad food", "usermail1.com", type1, new Date(), ticketStatus1, "csr1.com", new Date(), uuid2, new Date(), "app", new Date(), tags1, 5, "csr1.com", new Date());
        reports.add(report);
        int size = 1;
        String url = "/api/v1/reportsize?status=engaged";
        BDDMockito.given(reportInterface.getReportsByStatus("engaged")).willReturn(size);
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
