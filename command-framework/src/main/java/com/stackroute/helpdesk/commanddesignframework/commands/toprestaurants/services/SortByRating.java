package com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.services;

import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.model.Restaurant;
import org.json.simple.JSONObject;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class SortByRating implements Comparator<LinkedHashMap>{

    @Override
    public int compare(LinkedHashMap linkedHashMap, LinkedHashMap t1) {
       // System.out.println("..........sortings-1.............................................................................................");
       // System.out.println(linkedHashMap);
        if((double)(linkedHashMap.get("rating"))>(double)t1.get("rating")) {
            return -1;
        }
        else return 1;
    }

}

