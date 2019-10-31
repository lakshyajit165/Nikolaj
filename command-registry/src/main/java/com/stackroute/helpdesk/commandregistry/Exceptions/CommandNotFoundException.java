package com.stackroute.helpdesk.commandregistry.Exceptions;


import com.stackroute.helpdesk.commandregistry.entity.Commands;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommandNotFoundException extends RuntimeException
{
    public CommandNotFoundException(String commandName)
    {
        super(commandName);
    }
}
