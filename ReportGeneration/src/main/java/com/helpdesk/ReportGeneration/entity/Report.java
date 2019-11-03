package com.helpdesk.ReportGeneration.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Document(collection = "reports")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    private String id;
    private String ticketId;
    private String ticketName;
    private String raisedBy;
    private type type;
    private Date createdOn;
    private ticketStatus ticketStatus;
    private String assignedTo;
    private Date assignedTime;
    private String csrId;
    private Date updatedOn;
    private String entity;
    private Date responseTime;
    private String[] intent;
    private int rating;
    private String resolvedBy;
    private Date reopenDate;

    public Report(String ticketId, String ticketName, String raisedBy, com.helpdesk.ReportGeneration.entity.type type, Date createdOn, com.helpdesk.ReportGeneration.entity.ticketStatus ticketStatus, String assignedTo, Date assignedTime, String csrId, Date updatedOn, String entity, Date responseTime, String[] intent, int rating, String resolvedBy, Date reopenDate) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.raisedBy = raisedBy;
        this.type = type;
        this.createdOn = createdOn;
        this.ticketStatus = ticketStatus;
        this.assignedTo = assignedTo;
        this.assignedTime = assignedTime;
        this.csrId = csrId;
        this.updatedOn = updatedOn;
        this.entity = entity;
        this.responseTime = responseTime;
        this.intent = intent;
        this.rating = rating;
        this.resolvedBy = resolvedBy;
        this.reopenDate = reopenDate;
    }
}


