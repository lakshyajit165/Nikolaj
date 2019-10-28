package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private String name;
    private float costkm;
    private float costtime;
}

