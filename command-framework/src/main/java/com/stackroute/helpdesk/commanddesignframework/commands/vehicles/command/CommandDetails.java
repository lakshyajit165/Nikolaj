package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.command;

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
@Component("vehicleDetails")
public class CommandDetails implements ICommandDetail {
	private String name = "vehicle";
	private String description = "returns the rent for vehicles of the services";
	private String[] tags = {"vehicles", "rent"};
	private String usage;
	private String status;
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("vehicleType", "defines the type of vehicle to get the rent of", "false");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
