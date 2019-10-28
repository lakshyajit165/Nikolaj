package com.stackroute.helpdesk.intentcommandmapping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Command {
    @GraphId
    private Long id;
    private String commandName;
    private List<String> commandParameter;

    public Command(String commandName, List<String> commandParameter) {
        this.commandName = commandName;
        this.commandParameter = commandParameter;
    }
}
