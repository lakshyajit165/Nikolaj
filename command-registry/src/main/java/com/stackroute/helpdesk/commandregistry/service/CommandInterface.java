package com.stackroute.helpdesk.commandregistry.service;

import com.stackroute.helpdesk.commandregistry.commandstorage.model.CommandDetails;

import java.util.List;

public interface CommandInterface {
     List<CommandDetails> getCommands();
     CommandDetails addCommands(CommandDetails commands);


}
