package com.stackroute.helpdesk.commandregistry.repository;

import com.stackroute.helpdesk.commandregistry.entity.Commands;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;



public interface CommandRepository extends MongoRepository<Commands,String> {

    }

