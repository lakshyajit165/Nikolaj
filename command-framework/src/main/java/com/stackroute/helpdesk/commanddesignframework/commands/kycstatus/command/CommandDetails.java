package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.command;

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
@Component("kycstatusDetails")
public class CommandDetails implements ICommandDetail {
	private String name = "kycstatus";
	private String description = "returns the kycstatus of the user";
	private String[] tags = {"invoice", "kyc"};
	private String usage;
	private String status;
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("userId", "the unique userId of the user", "true");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
