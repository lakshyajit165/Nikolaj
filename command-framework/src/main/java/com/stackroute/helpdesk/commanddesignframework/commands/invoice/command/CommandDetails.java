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
	private String name = "last invoice";
	private String description = "returns the invoice of the user";
	private String[] tags = {"invoice", "billing", "payment"};
	private String usage = "/lastinvoice userId";
	private String status = "active";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("userId", "This takes the user unique id", "Required");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
