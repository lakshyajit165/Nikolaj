package com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private String restId;
    private String restName;
    private String url;
    private Address address;
    private String cuisines; //Paan, street food,
    private  String timings;
    private float rating;
    private String averageCostForTwo;
    private String[] offers;
    private String[] highlights; //"Delivery","Debit Card","No Seating Available","Cash","Takeaway Available","Credit Card","Gluten Free Options","Air Conditioned","Desserts and Bakes","Digital Payments Accepted"
    private String menuUrl;
    private List<Dish> dishes;
    private Dish bestDish;
}
