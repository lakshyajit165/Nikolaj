package com.stackroute.helpdesk.commanddesignframework.commands.dishes.controller;

import com.stackroute.helpdesk.commanddesignframework.commands.dishes.services.DishService;
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

@RestController
public class BestDishController {
    @Autowired
    private PdfConverter pdfConverter;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DishService dishService;

    private File file;

    @GetMapping("/bestdish")
    public ResponseEntity<Object> getDish(@RequestParam("param0") String restaurant) throws Exception {
        JSONObject jsonObject = restTemplate.getForObject("http://localhost:3000/result", JSONObject.class);
        String returnString = dishService.getBestDish(jsonObject, restaurant);



        return new ResponseEntity<>(returnString, HttpStatus.OK);

    }
}
