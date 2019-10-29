package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private String id;
    private String zoneid;
    private String registrationNo;
    private String insurance_no;
    private String status;
    private Type type;
    private Date time;
    private String lastServiceDate;
    private String vehiclePurchased;
}
