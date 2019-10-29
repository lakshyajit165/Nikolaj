package com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.refund.services.RefundService;
import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.model.Restaurant;
import com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.services.RestaurantService;
import com.stackroute.helpdesk.commanddesignframework.pdfConverter.PdfConverter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class TopRestaurantController {
    @Autowired
    private PdfConverter pdfConverter;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestaurantService restaurantService;

    private File file;

    @GetMapping("/toprestaurant")
    public ResponseEntity<Object> getRestaurants(@RequestParam("param0") String city) throws Exception {
//        String returnString = "your refund is inititated for userId= "+ticketId+"for userId2= "+userId;
//        refundService.getInvoice();
        System.out.println("..........etered.............................................................................................");
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        String returnString = restaurantService.getTopRestaurants(jsonObject, city);

//        Arrays.stream(restaurantList.toArray()).forEach((restaurant) -> {
//            LinkedHashMap address = (LinkedHashMap) ((LinkedHashMap)restaurant).get("address");
//            if(address.get("city").equals(city)) {
////                returnString = ((LinkedHashMap) restaurant).get("name");
//            }
//        }) ;
//
//        List<String> responseList = new ArrayList<>();
//        String response = "<html><body><p>"+returnString+"</p></body></html>";
//        file = pdfConverter.convertToPdf(returnString);
//        responseList.add(file.getAbsolutePath());

        return new ResponseEntity<>(returnString, HttpStatus.OK);

    }
}
