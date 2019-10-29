package com.helpdesk.ReportGeneration.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Csr {

    private int totalQueryTaken;
    private int totalQueryResolved;
    private double avgRating;
    private long counterTime;
    private int totalQueryReopen;
    private double successRate;
    private double normalizedQueriesTaken;
    private double efficiency;

}
