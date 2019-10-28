package com.stackroute.helpdesk.entity;





import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import java.util.Date;
import java.util.List;
import java.util.UUID;



@Document(collection = "tickets")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class TicketStructure implements Serializable {

    @Id
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
}

