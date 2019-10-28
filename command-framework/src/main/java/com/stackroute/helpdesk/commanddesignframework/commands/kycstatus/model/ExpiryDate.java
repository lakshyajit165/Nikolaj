package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpiryDate {
    private Chronology chronology;
    private String dayOfMonth;
    private String dayOfYear;
    private String era;
    private String leapYear;
    private String month;
    private String monthValue;
    private String year;
}
