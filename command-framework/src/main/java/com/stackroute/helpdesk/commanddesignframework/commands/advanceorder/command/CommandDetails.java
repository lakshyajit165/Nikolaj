package com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.command;

import com.stackroute.helpdesk.commanddesignframework.commands.ICommandDetail;
import com.stackroute.helpdesk.commanddesignframework.commands.offers.command.Parameter;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component("advanceOrder")
public class CommandDetails implements ICommandDetail {
	private String name = "advanceorder";
	private String description = "gives website path for ordering in advance";
	private String[] tags = {"confirmation of order"};
	private String usage = "/advanceorder";
	private String status="active";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		//Parameter parameter1 = new Parameter("userId", "unique userid of the user", "true");
		List<Parameter> parameterList = new ArrayList<>();
		//parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
