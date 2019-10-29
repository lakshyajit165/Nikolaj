package com.stackroute.helpdesk.commanddesignframework.commands.takefeedback.command;

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
@Component("takeFeedback")
public class CommandDetails implements ICommandDetail {
	private String name = "takefeedback";
	private String description = "take feedback from a user for a particular order";
	private String[] tags = {"confirmation of feedback received"};
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
