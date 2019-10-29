package com.stackroute.helpdesk.commanddesignframework.commands.offers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private String name;
    private String startDate;
    private String endDate;
    private String promocode;
    private int discountPercent;
}
