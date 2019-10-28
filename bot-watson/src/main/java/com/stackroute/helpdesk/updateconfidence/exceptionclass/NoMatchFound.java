package com.stackroute.helpdesk.updateconfidence.exceptionclass;

public class NoMatchFound extends RuntimeException {
    public NoMatchFound(String message){
        super(message);
    }
}
