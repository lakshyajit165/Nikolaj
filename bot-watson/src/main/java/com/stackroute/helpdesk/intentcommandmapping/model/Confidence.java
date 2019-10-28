package com.stackroute.helpdesk.intentcommandmapping.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "EXECUTED")
public class Confidence {
    @StartNode
    Intent intent;
    @EndNode
    Command commad;

    Integer confidence;
    String relationshipName;
}

