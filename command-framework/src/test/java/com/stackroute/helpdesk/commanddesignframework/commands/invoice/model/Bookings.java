package com.stackroute.helpdesk.commanddesignframework.commands.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    private String status;
    private String bookedAt;
    private String rideStartAt;
    private String rideEndAt;
    private String distance;
    private int duration;
    private String payment;
    private String paymentType;
    private String paymentProvider;
    private String registrationNo;
    private String vehicleName;
    private String riderName;
}
