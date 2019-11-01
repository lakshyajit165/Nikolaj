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
	private String description = "returns the list of all offers available to the user";
	private String[] tags = {"offers", "coupons"};
	private String usage = "/offers";
	private String status = "published";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		List<Parameter> parameterList = new ArrayList<>();
		this.parameterList=parameterList;
	}
}
