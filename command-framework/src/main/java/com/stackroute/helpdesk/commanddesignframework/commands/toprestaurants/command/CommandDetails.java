package com.stackroute.helpdesk.commanddesignframework.commands.toprestaurants.command;

import com.stackroute.helpdesk.commanddesignframework.commands.ICommandDetail;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component("toprestaurants")
public class CommandDetails implements ICommandDetail {
    private String name = "toprestaurants";
    private String description = "returns the top restaurant in a city";
    private String[] tags = {"restaurant"};
    private String usage;
    private String status;
    private List<Parameter> parameterList = new ArrayList<>();

    public void declareParameters(){
        Parameter parameter1 = new Parameter("city", "defines the city in which top restaurants required", "true");
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameter1);
        this.parameterList=parameterList;
    }
}

