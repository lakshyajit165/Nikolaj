package com.stackroute.helpdesk.messagingarchitecture;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ExchangeToExchangeBindingGenerator {


	private Library library;
	private Configurations configuration;
	private ExchangeGenerator exchangeGenerator;
	private QueueGenerator queueGenerator;

	@Autowired
	public ExchangeToExchangeBindingGenerator(Library library, Configurations configuration, QueueGenerator queueGenerator, ExchangeGenerator exchangeGenerator){
		this.library = library;
		this.configuration = configuration;
		this.queueGenerator = queueGenerator;
		this.exchangeGenerator = exchangeGenerator;
	}
//	exchange to exchange bindings (recieverExchangeName-SenderexchangeName)
//	ticket to csr
	@Bean
	public Binding csrTicketExchange() {
		return BindingBuilder.bind(exchangeGenerator.csrExchange()).to(exchangeGenerator.ticketExchange()).with(library.getTicket_csr_exchange());
	}
	//	ticket to notification
	@Bean
	public Binding notificationTicketExchange() {
		return BindingBuilder.bind(exchangeGenerator.notificationExchange()).to(exchangeGenerator.ticketExchange()).with(library.getTicket_notification_exchange());
	}
	//	ticket to reports
	@Bean
	public Binding reportsTicketExchange() {
		return BindingBuilder.bind(exchangeGenerator.reportsExchange()).to(exchangeGenerator.ticketExchange()).with(library.getTicket_reports_exchange());
	}
	//	ticket to notification
	@Bean
	public Binding reportsCsrExchange() {
		return BindingBuilder.bind(exchangeGenerator.reportsExchange()).to(exchangeGenerator.csrExchange()).with(library.getCsr_reports_exchange());
	}
	//	ticket to notification
	@Bean
	public Binding registryOptimusExchange() {
		return BindingBuilder.bind(exchangeGenerator.registryExchange()).to(exchangeGenerator.optimusExchange()).with(library.getOptimus_registry_exchange());
	}
	//	ticket to notification
	@Bean
	public Binding notificationOptimusExchange() {
		return BindingBuilder.bind(exchangeGenerator.notificationExchange()).to(exchangeGenerator.frameworkExchange()).with(library.getFramework_notification_exchange());
	}
}
