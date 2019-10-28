package com.stackroute.helpdesk.messagingarchitecture;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ExchangeGenerator {

	private Library library;
	private Configurations configuration;

	@Autowired
	public ExchangeGenerator(Library library, Configurations configuration){
		this.library = library;
		this.configuration = configuration;
	}

//	creating exchanges
	@Bean
	public TopicExchange csrExchange() {
		return new TopicExchange(library.getCsr_exchange());
	}

	@Bean
	public TopicExchange ticketExchange() {
		return new TopicExchange(library.getTicket_exchange());
	}

	@Bean
	public TopicExchange registryExchange() {
		return new TopicExchange(library.getRegistry_exchange());
	}

	@Bean
	public TopicExchange optimusExchange() {
		return new TopicExchange(library.getOptimus_exchange());
	}

	@Bean
	public TopicExchange reportsExchange() {
		return new TopicExchange(library.getReports_exchange());
	}

	@Bean
	public TopicExchange notificationExchange() {
		return new TopicExchange(library.getNotification_exchange());
	}

	@Bean
	public TopicExchange frameworkExchange(){
		return new TopicExchange(library.getFramework_exchange());
	}

}
