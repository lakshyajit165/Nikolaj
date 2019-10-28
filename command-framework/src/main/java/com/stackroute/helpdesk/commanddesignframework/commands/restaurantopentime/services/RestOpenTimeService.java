package com.stackroute.helpdesk.commanddesignframework.commands.restaurantopentime.services;

import com.stackroute.helpdesk.commanddesignframework.commands.restaurantopentime.model.Restaurant;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class RestOpenTimeService {
    public String getRestOpeningTime(JSONObject jsonObject, String restaurantId){
        List<Restaurant> restaurantList = (List<Restaurant>) jsonObject.get("restaurants");
        int flag = 0;
        String returnString = " ";
        Restaurant restaurant1 = new Restaurant();

//        List<String> stringList = new ArrayList<>();
//        Arrays.stream(restaurantList.toArray()).forEach((restaurant)-> {
//            if(((LinkedHashMap)restaurant).get("restName").equals(restaurantName)) {
//                stringList.add((String)((LinkedHashMap) restaurant).get("bestDish"));
//            }
//        });
//        returnString += stringList.get(0);

        return  returnString;
    }
}
