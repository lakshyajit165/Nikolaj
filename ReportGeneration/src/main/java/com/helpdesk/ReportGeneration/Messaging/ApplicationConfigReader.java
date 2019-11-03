package com.helpdesk.ReportGeneration.Messaging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
public class ApplicationConfigReader {


    //report exchanges....
    @Value("${reports.exchange.name}")
    private String reports_exchange;

    //my queues
    @Value("${ticket-details.queue.name}")
    private String reports_ticketdetails_queue_subscribe;
    @Value("${csr-details.queue.name}")
    private String reports_csrdetails_queue_subscribe;

    //my routing keys
    @Value("${reports.csroutlierreport.generated.routing.key}")
    private String reports_csroutlierreport_generated;
    @Value("${reports.servicereport.generated.routing.key}")
    private String reports_servicereport_generated;
    @Value("${reports.botreliabilityreport.generated.routing.key}")
    private String reports_botreliabilityreport_generated;
    @Value("${reports.ticketstatusreport.generated.routing.key}")
    private String reports_ticketstatusreport_generated;


}
