package com.stackroute.helpdesk.commandregistry.controller;

import com.stackroute.helpdesk.commandregistry.commandstorage.model.CommandDetails;
import com.stackroute.helpdesk.commandregistry.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/commandregistry/commands")
public class CommandRegistryController {

    @Autowired
    private CommandRepository commandRepository;

    CommandRegistryController(CommandRepository commandRepository)
    {
        this.commandRepository=commandRepository;
    }
    HashMap<String, Object> responseObject;

    /**
     *
     * get all commands
     *
     *
     */

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getCommands() {

        List<CommandDetails> commands = commandRepository.findAll();

        responseObject = new HashMap<>();
        responseObject.put("result", commands);
        responseObject.put("message", "Success!");
        responseObject.put("error", "false");

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
