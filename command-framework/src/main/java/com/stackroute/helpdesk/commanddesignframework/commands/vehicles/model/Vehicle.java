package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vehicle {

    private String id = UUID.randomUUID().toString().substring(30);
    private String zoneId;
    private String registrationNo;
    private String insuranceNo;
    private VehicleStatus status;
    private VehicleType vehicleType;
    private LocalDate lastServiceDate;
    private LocalDate purchasedDate;
    private String chassisNumber;

    public Vehicle(String id, String zoneId, String registrationNo) {
        this.id = id;
        this.zoneId = zoneId;
        this.registrationNo = registrationNo;
    }

}
