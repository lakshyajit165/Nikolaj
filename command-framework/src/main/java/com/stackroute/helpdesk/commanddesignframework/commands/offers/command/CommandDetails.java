package com.stackroute.helpdesk.commanddesignframework.commands.offers.command;

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
@Component("offersDetails")
public class CommandDetails implements ICommandDetail {
	private String name = "offers";
	private String description = "returns the offers available to the user";
	private String[] tags = {"offers", "coupons"};
	private String usage;
	private String status;
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("userId", "unique userid of the user", "true");
		Parameter parameter2 = new Parameter("numberOfCoupons", "number of the offers to get the details of", "true");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		parameterList.add(parameter2);
		this.parameterList=parameterList;
	}
}
