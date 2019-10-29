package com.stackroute.helpdesk.commanddesignframework.commands.refund.command;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parameter {
	private String parameterName;
	private String parameterDesciption;
	private String required;
}
