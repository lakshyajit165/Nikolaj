package com.stackroute.helpdesk.commanddesignframework.commands.refund.command;

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
@Component("refundDetails")
public class CommandDetails implements ICommandDetail {
	private String name = "initiaterefund";
	private String description = "returns the confirmation about the refund initiated";
	private String[] tags = {"refund", "payment"};
	private String usage = "/refund userId, ticketId";
	private String status = "active";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("userId", "takes the unique id of the user", "Required");
		Parameter parameter2 = new Parameter("ticketId", "takes the ticket id to initiate the refund on", "Required");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		parameterList.add(parameter2);
		this.parameterList=parameterList;
	}
}
