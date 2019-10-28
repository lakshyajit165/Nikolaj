package com.stackroute.helpdesk.csrservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "csr")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CsrStructure {

    @Id
    private String id;

    private String uuid;

    private String csrmail;

    private TicketStatus ticketStatus;

    private String ticketId;

    private String ticketName;

    private int rating;

    private Date createdOn;

    private Date updatedOn;

    private Status status;
}
