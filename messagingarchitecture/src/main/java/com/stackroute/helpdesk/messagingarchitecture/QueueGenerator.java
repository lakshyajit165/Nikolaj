package com.stackroute.helpdesk.messagingarchitecture;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class QueueGenerator {

	private Library library;
	private Configurations configuration;

	@Autowired
	public QueueGenerator(Library library, Configurations configuration){
		this.library = library;
		this.configuration = configuration;
	}
//	creating queues
//	socket server
	@Bean
	public Queue socketserverTicketClosedQueueSubscribe(){
		return new Queue(library.getSocketserver_closed_queue_subscribe());
	}
//	registry
	@Bean
	public Queue registryNoIntentRecievedQueueSubscribe() {
		return new Queue(library.getRegistry_nointentrecieved_queue_subscribe());
	}

	@Bean
	public Queue registryNoCommandRecievedQueueSubscribe() {
		return new Queue(library.getRegistry_nocommandrecieved_queue_subscribe());
	}
	//	framework
	@Bean
	public Queue frameworkExecutedQueueSubscribe() {
		return new Queue(library.getFramework_executed_queue_subscribe());
	}
	//	csr
	@Bean
	public Queue csrRequestedQueueSubscribe() {
		return new Queue(library.getCsr_requested_queue_subscribe());
	}

	@Bean
	public Queue csrUpdatedQueueSubscribe() {
		return new Queue(library.getCsr_updated_queue_subscribe());
	}

	//	reports
	@Bean
	public Queue reportsTicketDetailsQueueSubscribe() {
		return new Queue(library.getReports_ticketdetails_queue_subscribe());
	}

	@Bean
	public Queue reportsCsrDetailsQueueSubscribe() {
		return new Queue(library.getReports_csrdetails_queue_subscribe());
	}

	//notification
	@Bean
	public Queue notificationSentQueueSubscribe() {
		return new Queue(library.getNotification_sent_queue_subscribe());
	}
}
