package com.stackroute.helpdesk.commandregistry.commandstorage.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component("reportStructure")
public class ReportDetails {
String typeOfReport;
String entity;
Intent intent[];
}
