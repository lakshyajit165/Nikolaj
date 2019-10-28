package com.stackroute.helpdesk.commanddesignframework.commands.offers.service;

import com.stackroute.helpdesk.commanddesignframework.commands.offers.model.Offer;
import com.stackroute.helpdesk.commanddesignframework.commands.offers.model.Offers;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class OfferService {
     private List<String> offerList;

    public List<String> getAllOffers(JSONObject jsonObject){
        List<Offers> offersList = (List<Offers>) jsonObject.get("data");
        offerList = new ArrayList<>();
        Arrays.stream(offersList.toArray()).forEach((offers) -> {
           offerList.add(getResult((LinkedHashMap) offers));
        });
        return offerList;
    }

    public String getResult(LinkedHashMap offer){
        return "Get " + offer.get("discountPercent") + "% on " + (String)offer.get("name") + " from " +
                (String)offer.get("startDate") + " to " + (String)offer.get("endDate");
    }
}
