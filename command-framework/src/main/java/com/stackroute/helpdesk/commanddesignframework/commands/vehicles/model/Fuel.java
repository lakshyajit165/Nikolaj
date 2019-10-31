package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.model;

import lombok.*;
import java.util.UUID;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fuel {
    private String id = UUID.randomUUID().toString().substring(30);
    private String name;
    private Float fuelCost;

}

