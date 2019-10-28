package com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.services;

import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.model.Restaurant;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService {
    public String getTopRestaurants(JSONObject jsonObject, String city){
        System.out.println("..........service-1.............................................................................................");
        List<Restaurant> restaurantList = (List<Restaurant>) jsonObject.get("restaurants");

        List<LinkedHashMap> linkedHashMapList = new ArrayList<>();
        Arrays.stream(restaurantList.toArray()).forEach(restraunt -> {
            linkedHashMapList.add((LinkedHashMap) restraunt);
            //System.out.println(((LinkedHashMap)restraunt).get("rating"));
        });

        ListIterator<Restaurant> restaurantListIterator = restaurantList.listIterator();

//        Arrays.sort(restaurantList, (a, b) -> a.name.compareTo(b.name));
        System.out.println(linkedHashMapList);
        System.out.println("..........service-2.............................................................................................");
        Collections.sort(linkedHashMapList, new SortByRating());
        System.out.println("..........service-3.............................................................................................");
        System.out.println(linkedHashMapList);
        List<LinkedHashMap> particularCityRestaurants = new ArrayList<>();
        Arrays.stream(linkedHashMapList.toArray()).forEach(restraunt -> {
//            linkedHashMapList.add((LinkedHashMap) restraunt);
//            System.out.println(((Restaurant)restraunt).getRating());
            if(((LinkedHashMap)((LinkedHashMap)restraunt).get("address")).get("city").equals(city)) {
                particularCityRestaurants.add((LinkedHashMap) restraunt);
            }
        });



        String returnString = "Top restaurants in "+city+" is: "+particularCityRestaurants.get(0).get("restName")+" with rating "+particularCityRestaurants.get(0).get("rating");

        return  returnString;
    }
}

