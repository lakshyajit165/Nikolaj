package com.stackroute.helpdesk.commanddesignframework.commands.restaurantopentime.command;

import com.stackroute.helpdesk.commanddesignframework.commands.ICommandDetail;
import com.stackroute.helpdesk.commanddesignframework.commands.cancelorder.command.command.Parameter;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component("restaurantOpenTime")
public class CommandDetails implements ICommandDetail {
	private String name = "restopentime";
	private String description = "take feedback from a user for a particular order";
	private String[] tags = {"restauorant opening time"};
	private String usage = "/restopentime restaurantId";
	private String status="active";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("restaurantId", "Unique Id for a restaurant", "Required");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
