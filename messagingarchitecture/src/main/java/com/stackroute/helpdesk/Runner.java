package com.stackroute.helpdesk;

import com.stackroute.helpdesk.messaging.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private Sender sender;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("in runner creating the queues");
		sender.getConfig("starting", "starting");
	}
}
