package com.stackroute.helpdesk.commandregistry.trackissueservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Intent {

    String uniqueId;
    String ticketName;
    String ticketId;
    String intent;
    String createdOn;
    String updatedOn;
    String userId;
    String entity;
    String reportType;
}
