package com.stackroute.helpdesk.messagingarchitecture;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class QueueToExchangeBindingGenerator {
	private Library library;
	private Configurations configuration;
	private ExchangeGenerator exchangeGenerator;
	private QueueGenerator queueGenerator;
	@Autowired
	public QueueToExchangeBindingGenerator(Library library, Configurations configuration, QueueGenerator queueGenerator, ExchangeGenerator exchangeGenerator){
		this.library = library;
		this.configuration = configuration;
		this.queueGenerator = queueGenerator;
		this.exchangeGenerator = exchangeGenerator;
	}
//	queue to exchange bindings
//	socket server
	@Bean
	public Binding ticketClosed() {
		return BindingBuilder.bind(queueGenerator.socketserverTicketClosedQueueSubscribe()).to(exchangeGenerator.socketserverExchange()).with(library.getTicket_ticket_closed());
	}
//	command framework
	@Bean
	public Binding frameworkCommandExecuted() {
		return BindingBuilder.bind(queueGenerator.frameworkExecutedQueueSubscribe()).to(exchangeGenerator.frameworkExchange()).with(library.getFramework_command_executed());
	}
	//	notifications
	@Bean
	public Binding notficationMailSent() {
		return BindingBuilder.bind(queueGenerator.notificationSentQueueSubscribe()).to(exchangeGenerator.notificationExchange()).with(library.getNotification_mail_sent());
	}
	//  reports
	@Bean
	public Binding reportsCsrOutlierReportGenerated() {
		return BindingBuilder.bind(queueGenerator.reportsCsrDetailsQueueSubscribe()).to(exchangeGenerator.reportsExchange()).with(library.getReports_csroutlierreport_generated());
	}
	@Bean
	public Binding reportsServiceReportGenerated() {
		return BindingBuilder.bind(queueGenerator.reportsTicketDetailsQueueSubscribe()).to(exchangeGenerator.reportsExchange()).with(library.getReports_servicereport_generated());
	}
	@Bean
	public Binding reportsBotReliabilityReportGenerated() {
		return BindingBuilder.bind(queueGenerator.reportsTicketDetailsQueueSubscribe()).to(exchangeGenerator.reportsExchange()).with(library.getReports_botreliabilityreport_generated());
	}
	@Bean
	public Binding reportsTicketStatusReportGenerated() {
		return BindingBuilder.bind(queueGenerator.reportsTicketDetailsQueueSubscribe()).to(exchangeGenerator.reportsExchange()).with(library.getReports_ticketstatusreport_generated());
	}

	//	registry
	@Bean
	public Binding registryNoIntentReportRecieved() {
		return BindingBuilder.bind(queueGenerator.registryNoIntentRecievedQueueSubscribe()).to(exchangeGenerator.registryExchange()).with(library.getRegistry_nointentreport_recieved());
	}
	@Bean
	public Binding registryNoCommandReportRecieved() {
		return BindingBuilder.bind(queueGenerator.registryNoCommandRecievedQueueSubscribe()).to(exchangeGenerator.registryExchange()).with(library.getRegistry_nocommandreport_recieved());
	}

	//	csr
	@Bean
	public Binding csrTicketRequested() {
		return BindingBuilder.bind(queueGenerator.csrRequestedQueueSubscribe()).to(exchangeGenerator.csrExchange()).with(library.getCsr_ticket_requested());
	}
	@Bean
	public Binding csrCsrUpdated() {
		return BindingBuilder.bind(queueGenerator.csrUpdatedQueueSubscribe()).to(exchangeGenerator.csrExchange()).with(library.getCsr_csr_updated());
	}
}
