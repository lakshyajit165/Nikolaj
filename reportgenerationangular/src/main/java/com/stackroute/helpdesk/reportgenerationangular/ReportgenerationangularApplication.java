package com.stackroute.helpdesk.reportgenerationangular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@RefreshScope
@SpringBootApplication
public class ReportgenerationangularApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportgenerationangularApplication.class, args);
	}

}
