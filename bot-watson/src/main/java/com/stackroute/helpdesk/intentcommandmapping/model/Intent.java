package com.stackroute.helpdesk.intentcommandmapping.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import javax.validation.GroupSequence;
import java.util.List;

@Data
@NoArgsConstructor
@NodeEntity
public class Intent {
    @GraphId
    private Long id;
    private String intentName;
    private IntentStatus intentStatus;

    @Relationship(type="EXECUTED",direction = Relationship.INCOMING)
    private List<Command> commands;


    public Intent(String intentName, IntentStatus intentStatus) {
        this.intentName = intentName;
        this.intentStatus = intentStatus;
    }
}
