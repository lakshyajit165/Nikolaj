package com.stackroute.helpdesk.commandregistry.service;

import com.stackroute.helpdesk.commandregistry.commandstorage.model.CommandDetails;
import com.stackroute.helpdesk.commandregistry.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommandService implements CommandInterface {
    @Autowired
    private CommandRepository commandRepository;

    public List<CommandDetails> getCommands() {
        return commandRepository.findAll();


        //  public Commands patchCommands(Commands commands){
        // return commandRepository.save(commands);
         }
        public CommandDetails addCommands(CommandDetails commands)
        {
            return commandRepository.save(commands);
        }

//        public List<Commands> patchCommands (Commands commands){
//            return commandRepository.findAll();
//
//        }

    }
