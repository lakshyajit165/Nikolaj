package com.stackroute.helpdesk.commanddesignframework.commands.invoice.command;

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
@Component("invoiceDetails")
public class CommandDetails implements ICommandDetail {
	private String name = "invoice";
	private String description = "returns the invoice of the user";
	private String[] tags = {"invoice", "billing", "payment"};
	private String usage;
	private String status;
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("paramName", "paramDesc", "true");
		Parameter parameter2 = new Parameter("paramName", "paramDesc", "true");
		Parameter parameter3 = new Parameter("paramName", "paramDesc", "true");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		parameterList.add(parameter2);
		parameterList.add(parameter3);
		this.parameterList=parameterList;
	}
}
