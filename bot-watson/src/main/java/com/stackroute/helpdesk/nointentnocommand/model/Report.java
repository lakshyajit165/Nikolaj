package com.stackroute.helpdesk.nointentnocommand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mockito.InjectMocks;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Reports")
public class Report {
    @Id
    private String ticketId;
    private String ticketName;
    private String intent;
    private Date createdOn;
    private Date updatedOn;
    private String userId;
    private String entity;
    private ReportType reportType;
    private String commandName;

//    public Report(String ticketName, String ticketId, String intent, Date createdOn, Date updatedOn, String userId, String entity, ReportType reportType, String commandName) {
//        this.ticketName = ticketName;
//        this.ticketId = ticketId;
//        this.intent = intent;
//        this.createdOn = createdOn;
//        this.updatedOn = updatedOn;
//        this.userId = userId;
//        this.entity = entity;
//        this.reportType = reportType;
//        this.commandName = commandName;
//    }
}
