package com.stackroute.helpdesk.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                 .apis(RequestHandlerSelectors.basePackage("com.stackroute.helpdesk"))
                .paths(regex("/optimus/api/v1.*"))
                .build()
                .apiInfo(metaInfo());
    }
    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Intent command mapping",
                "Documentation for Restful on Intent command mapping",
                "1.0",
                "Terms of Service",
                new Contact("Stackroute","http://www.stackroute.in/","maprana1@in.ibm.com"),
                "Apache License Version 2.0",
                "http://www.apache.org/licensen.html"
        );
        return apiInfo;

    }


}
