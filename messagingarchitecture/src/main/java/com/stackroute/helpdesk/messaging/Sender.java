package com.stackroute.helpdesk.messaging;

import com.stackroute.helpdesk.messagingarchitecture.Library;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sender {

	@Autowired
	@Qualifier("rabbitTemplate")
	private RabbitTemplate rabbitTemplate;

//	@Autowired
	private MessageSender messageSender;

	@Autowired
	public void Sender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	@Autowired
	public void setApplicationConfig(Library applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@Autowired
	private Library applicationConfig;


	@GetMapping("/send/{exchangeName}/{routingKey}")
	public String getConfig(@PathVariable String exchangeName, @PathVariable String routingKey){
		messageSender.sendMessage(rabbitTemplate ,exchangeName ,routingKey, new ResponseEntity("data", HttpStatus.OK));
		return "message sent";
	}
}
