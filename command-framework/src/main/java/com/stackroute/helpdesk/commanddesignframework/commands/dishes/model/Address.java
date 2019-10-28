package com.stackroute.helpdesk.commanddesignframework.commands.dishes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String locality;
    private String city;
    private int cityId;
    private String state;
    private  int pincode;
}
