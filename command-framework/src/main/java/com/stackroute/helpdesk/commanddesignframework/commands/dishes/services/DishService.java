package com.stackroute.helpdesk.commanddesignframework.commands.dishes.services;

import com.stackroute.helpdesk.commanddesignframework.commands.dishes.model.Restaurant;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DishService {
    public String getBestDish(JSONObject jsonObject, String restaurantName){
        List<Restaurant> restaurantList = (List<Restaurant>) jsonObject.get("restaurants");
        //ListIterator<Restaurant> restaurantListIterator = restaurantList.listIterator();
        int flag = 0;
        String returnString = "Best dish in "+restaurantName+" is: ";
        Restaurant restaurant1 = new Restaurant();

        List<String> stringList = new ArrayList<>();
        Arrays.stream(restaurantList.toArray()).forEach((restaurant)-> {
            if(((LinkedHashMap)restaurant).get("restName").equals(restaurantName)) {
                stringList.add((String)((LinkedHashMap) restaurant).get("bestDish"));
            }
        });
        returnString += stringList.get(0);

        return  returnString;
    }

}
