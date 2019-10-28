package com.helpdesk.ReportGeneration.entity;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Service {

    private String entity;
    private int queriesRaised;
    private int queriesResolved;
    private long leadTime;    //total time to resolve the ticket

}