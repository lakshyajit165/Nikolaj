package com.stackroute.helpdesk.commanddesignframework.commands.refund.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Invoice {
    private String Id;
    private String status;
    private List<Rider> rider;
    private SourceZone sourceZones;
    private List<Vehicle> vehicles;
    private List<PaymentMethod> paymentMethods;
    private List<SourceZone> destinationZones;
    private String bookedAt;
    private String rideStartAt;
    private String rideEndAt;
    private String distance;
    private int duration;
    private String payment;
}