package com.stackroute.helpdesk.ticketservice.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "tickets")
@Data
public class TicketModel {

    @Id
    private String id;

    private UUID uuid;

    private String query;
    private List<String> intent;
    private String raisedBy;
    private Date createdOn;
    private Date assignedTime;

    private Date updatedOn;
    private String assignedTo;
    private int rating;
    private String entity;

    private Type type;
    private Status status;
    private ResponseType responseType;

    private String resolvedBy;

    private Date reopenTime;
 }
