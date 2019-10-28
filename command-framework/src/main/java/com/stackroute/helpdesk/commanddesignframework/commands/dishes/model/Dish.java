package com.stackroute.helpdesk.commanddesignframework.commands.dishes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private String dishId;
    private String dishName;
    private double price;
    private String dishType;
    private float dishRating;

}
