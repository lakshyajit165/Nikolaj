package com.stackroute.helpdesk.messagingarchitecture;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class Library {

    //  exchanges names
    @Value("${registry.exchange.name}")
    private String registry_exchange;
    @Value("${framework.exchange.name}")
    private String framework_exchange;
    @Value("${csr.exchange.name}")
    private String csr_exchange;
    @Value("${optimus.exchange.name}")
    private String optimus_exchange;
    @Value("${notification.exchange.name}")
    private String notification_exchange;
    @Value("${reports.exchange.name}")
    private String reports_exchange;
    @Value("${ticket.exchange.name}")
    private String ticket_exchange;
    @Value("${socketserver.exchange.name}")
    private String socketserver_exchange;

//    queue names
//    socketserver queues names
    @Value("${socketserver-ticket-closed.queue.name}")
    private String socketserver_closed_queue_subscribe;
//    command-registry queues names
    @Value("${no-intent-report-recieved.queue.name}")
    private String registry_nointentrecieved_queue_subscribe;
    @Value("${no-command-report-recieved.queue.name}")
    private String registry_nocommandrecieved_queue_subscribe;
    //    command-framework queues names
    @Value("${command-executed.queue.name}")
    private String framework_executed_queue_subscribe;
    //    csr-service queues names
    @Value("${ticket-requested.queue.name}")
    private String csr_requested_queue_subscribe;
    @Value("${csr-performance-updated.queue.name}")
    private String csr_updated_queue_subscribe;
    //      report generation queues names
    @Value("${ticket-details.queue.name}")
    private String reports_ticketdetails_queue_subscribe;
    @Value("${csr-details.queue.name}")
    private String reports_csrdetails_queue_subscribe;

//    notification services
    @Value("${mail-sent.queue.name}")
    private String notification_sent_queue_subscribe;


//    queue to exchange binding keys
//    for socketserver
    @Value("${sockerserver.ticket.closed.routing.key}")
    private String ticket_ticket_closed;
//    for framework
    @Value("${framework.command.executed.routing.key}")
    private String framework_command_executed;
//    ticket
    @Value("${ticket.ticket.created.routing.key}")
    private String ticket_ticket_created;
    @Value("${ticket.ticket.updated.routing.key}")
    private String ticket_ticket_updated;
//    csr
    @Value("${csr.ticket.requested.routing.key}")
    private String csr_ticket_requested;
    @Value("${csr.csr.updated.routing.key}")
    private String csr_csr_updated;
//    optimus
    @Value("${optimus.nointentreport.generated.routing.key}")
    private String optimus_nointentreport_generated;
    @Value("${optimus.nocommandreport.generated.routing.key}")
    private String optimus_nocommandreport_generated;
//    registry
    @Value("${registry.nointentreport.recieved.routing.key}")
    private String registry_nointentreport_recieved;
    @Value("${registry.nocommandreport.recieved.routing.key}")
    private String registry_nocommandreport_recieved;
//    notifications
    @Value("${notification.mail.sent.routing.key}")
    private String notification_mail_sent;
//    reports
    @Value("${reports.csroutlierreport.generated.routing.key}")
    private String reports_csroutlierreport_generated;
    @Value("${reports.servicereport.generated.routing.key}")
    private String reports_servicereport_generated;
    @Value("${reports.botreliabilityreport.generated.routing.key}")
    private String reports_botreliabilityreport_generated;
    @Value("${reports.ticketstatusreport.generated.routing.key}")
    private String reports_ticketstatusreport_generated;
//    exchange to exchange binding keys
    @Value("${ticket.csr.exchange.key}")
    private String ticket_csr_exchange;
    @Value("${ticket.notification.exchange.key}")
    private String ticket_notification_exchange;
    @Value("${ticket.reports.exchange.key}")
    private String ticket_reports_exchange;
    @Value("${csr.reports.exchange.key}")
    private String csr_reports_exchange;
    @Value("${optimus.registry.exchange.key}")
    private String optimus_registry_exchange;
    @Value("${framework.notification.exchange.key}")
    private String framework_notification_exchange;
    @Value("${ticket.socketserver.exchange.key}")
    private String ticket_socketserver_exchange;
}