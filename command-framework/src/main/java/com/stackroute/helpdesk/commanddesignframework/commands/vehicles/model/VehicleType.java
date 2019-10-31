package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.model;

import lombok.*;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class VehicleType {

    private String id = UUID.randomUUID().toString().substring(30);
    private String name;
    private float costPerKm;
    private float costPerMin;
    private String vehicleCC;
    private float mileage;
    private String url;
    private Fuel fuel;
    private float baseFare;


    public VehicleType(String name, Float costPerKm, Float costPerMin) {

        this.name = name;
        this.costPerKm = costPerKm;
        this.costPerMin = costPerMin;
    }

}
