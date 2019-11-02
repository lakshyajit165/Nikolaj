package com.stackroute.helpdesk.commanddesignframework.commands.vehicles.command;

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
@Component("vehicleDetails")
public class CommandDetails implements ICommandDetail {
	private String name = "vehiclerent";
	private String description = "returns the rent for vehicles of the umove";
	private String[] tags = {"vehicles", "rent"};
	private String usage = "/vehiclerent";
	private String status = "active";
	private List<Parameter> parameterList = new ArrayList<>();

	public void declareParameters(){
		List<Parameter> parameterList = new ArrayList<>();
		this.parameterList=parameterList;
	}
}
