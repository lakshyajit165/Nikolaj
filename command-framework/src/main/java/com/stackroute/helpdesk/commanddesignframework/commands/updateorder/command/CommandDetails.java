package com.stackroute.helpdesk.commanddesignframework.commands.updateorder.command;

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
@Component("updateOrder")
public class CommandDetails implements ICommandDetail {
	private String name = "updateorder";
	private String description = "updating the address for a particular order";
	private String[] tags = {"confirmation of updation of order"};
	private String usage = "/updateorder orderId, newAddress";
	private String status="active";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		Parameter parameter1 = new Parameter("orderId", "Unique Id for a order", "Required");
		Parameter parameter2 = new Parameter("newAddress", "New address for delivery of order", "Required");
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter1);
		this.parameterList=parameterList;
	}
}
