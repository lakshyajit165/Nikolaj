package com.stackroute.helpdesk.commanddesignframework.commands.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
//    private String Id;
    private String status;
    private List<Rider> rider;
    private Zones sourceZones;
    private List<Vehicle> vehicles;
    private List<PaymentMethod> paymentMethods;
    private List<Zones> destinationZones;
    private String bookedAt;
    private String rideStartAt;
    private String rideEndAt;
    private String distance;
    private int duration;
    private String payment;
}