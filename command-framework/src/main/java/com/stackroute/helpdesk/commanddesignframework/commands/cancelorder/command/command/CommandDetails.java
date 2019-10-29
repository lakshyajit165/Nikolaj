package com.stackroute.helpdesk.commanddesignframework.commands.cancelorder.command.command;

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
@Component("cancelOrder")
public class CommandDetails implements ICommandDetail {
	private String name = "cancelorder";
	private String description = "cancel a particular order";
	private String[] tags = {"confirmation of cancelation of order"};
	private String usage;
	private String status;
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("paramName", "paramDesc", "true");
		Parameter parameter2 = new Parameter("paramName", "paramDesc", "true");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
