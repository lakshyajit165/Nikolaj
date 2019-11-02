package com.stackroute.helpdesk.commanddesignframework.commands.dishes.command;

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
@Component("bestDish")
public class CommandDetails implements ICommandDetail {
	private String name = "bestdish";
	private String description = "returns the best dish in a restaurant";
	private String[] tags = {"best dish", "rating"};
	private String usage = "/bestdish restaurantId";
	private String status="active";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("restaurantId", "Id for a particular restaurant", "Required");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
