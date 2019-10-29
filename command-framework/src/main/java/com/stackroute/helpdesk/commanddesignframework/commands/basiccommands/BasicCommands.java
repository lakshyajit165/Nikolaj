package com.stackroute.helpdesk.commanddesignframework.commands.basiccommands;

import com.stackroute.helpdesk.commanddesignframework.commands.ICommandDetail;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarInputStream;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

@RestController
@RequestMapping("")
public class BasicCommands {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private HashMap<String,String> basicCommandsList;
    private Map<RequestMappingInfo, HandlerMethod> listOfCommands;

    @Autowired
    public BasicCommands(RequestMappingHandlerMapping requestMappingHandlerMapping){
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @GetMapping("/basic-commands")
    public ResponseEntity<List<ICommandDetail>> getCommandDetails() throws ClassNotFoundException {
        List<ICommandDetail> commandDetailsList = new ArrayList<>();
        Package commandPackage = Class.forName(this.getClass().getName()).getPackage();
        Package listOfPackages[] = commandPackage.getPackages();
        System.out.println(listOfPackages.length);
        Arrays.stream(listOfPackages).forEach((packageElement) -> {
            System.out.println(packageElement);
            if(packageElement.getName().endsWith("command")) {
                String commandClass = packageElement.getName()+".CommandDetails";
                try {
                    System.out.println(commandClass);
                    Class commandDeatilsClass = Class.forName(commandClass);
                    ICommandDetail command = (ICommandDetail)commandDeatilsClass.newInstance();
                    command.declareParameters();
                    commandDetailsList.add((ICommandDetail) command);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
        return new ResponseEntity<>(commandDetailsList, HttpStatus.OK);
    }
}
