package com.stackroute.helpdesk.commandregistry.service;

import com.stackroute.helpdesk.commandregistry.entity.Commands;

import java.util.List;

public interface CommandInterface {
     List<Commands> getCommands();
     Commands addCommands(Commands commands);


}
