package com.stackroute.helpdesk.commandregistry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class CommandAlreadyExistsException extends RuntimeException {

    public CommandAlreadyExistsException(String commandName){super(commandName);}
}
