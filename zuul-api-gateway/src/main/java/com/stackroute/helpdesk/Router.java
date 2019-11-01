package com.stackroute.helpdesk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/command-registry-angular")
    public String redirectToCommandRegistryUI(){
        return "forward:command-registry-angular";
    }

    @GetMapping("/")
    public String redirectToCsrUi(){
        return "forward:csr-angular";
    }

    @GetMapping("/report-angular")
    public String redirectToReportGenerationUi(){
        return "forward:report-angular";
    }

}
