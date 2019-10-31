package com.stackroute.helpdesk.commandregistry.repository;

import com.stackroute.helpdesk.commandregistry.commandstorage.model.CommandDetails;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CommandRepository extends MongoRepository<CommandDetails,String> {

    }

