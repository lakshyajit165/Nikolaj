package com.stackroute.helpdesk.mailservice.messaging.model;


import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TicketStructure implements Serializable {

    private String id;

    private String uuid;

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

//    public Date getAssignedTime() {
//        return this.assignedTime;
//    }


}

