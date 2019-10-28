package com.stackroute.helpdesk.commandregistry.commandstorage.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Intent {
    String uniqueId;
    String ticketName;
    String ticketId;
    String intent;
    Date createdOn;
    Date updatedOn;
    String userId;
    String entity;
    String reportType;
}
