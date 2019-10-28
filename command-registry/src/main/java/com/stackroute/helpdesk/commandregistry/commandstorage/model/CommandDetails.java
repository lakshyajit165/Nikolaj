package com.stackroute.helpdesk.commandregistry.commandstorage.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component("commandStructure")
public class CommandDetails {
	private String name ;
	private String description ;
	private List<String> tags ;
	private String usage;
	private String status;
	private List<Parameter> parameterList;
}
