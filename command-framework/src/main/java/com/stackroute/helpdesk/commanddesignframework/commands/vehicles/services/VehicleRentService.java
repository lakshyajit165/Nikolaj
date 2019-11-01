//package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.services;
//
//import org.json.simple.JSONObject;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class VehicleRentService {
//
//    public List<String> getAllTypeVehicleRent(JSONObject jsonObject){
//        List<Vehicle> vehiclesList = (List<Vehicle>) jsonObject.get("data");
//        List<String> typeList = new ArrayList<>();
//        Set<String> vehicleTypes = new HashSet<>();
//        Arrays.stream(vehiclesList.toArray()).forEach((vehicle) -> {
//            LinkedHashMap type = (LinkedHashMap) ((LinkedHashMap)vehicle).get("type");
//            if(!vehicleTypes.contains((String) type.get("name"))){
//                vehicleTypes.add((String)type.get("name"));
//                typeList.add(getResultData(type));
//            }
//        });
//        return typeList;
//    }
//
//    public List<String> getParticularTypeVehicleRent(JSONObject jsonObject, String vehicleType){
//        List<Vehicle> vehiclesList = (List<Vehicle>) jsonObject.get("data");
//        Set<String> vehicleTypes = new HashSet<>();
//        List<String> typeOfVehicle = new ArrayList<>();
//        Arrays.stream(vehiclesList.toArray()).forEach((vehicle) -> {
//            LinkedHashMap type = (LinkedHashMap) ((LinkedHashMap)vehicle).get("type");
//            if((!vehicleTypes.contains((String) type.get("name"))) && (type.get("name").equals(vehicleType))){
//                vehicleTypes.add((String)type.get("name"));
//                typeOfVehicle.add(getResultData(type));
//            }
//        });
//        return typeOfVehicle;
//    }
//
//    public String getResultData(LinkedHashMap type){
//        return (String) type.get("name")+"'s cost per km = " + (int) type.get("costkm") + " and cost per minute = " + (int) type.get("costtime");
//    }
//}
