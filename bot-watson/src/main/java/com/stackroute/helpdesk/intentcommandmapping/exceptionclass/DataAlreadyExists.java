package com.stackroute.helpdesk.intentcommandmapping.exceptionclass;

public class DataAlreadyExists extends RuntimeException {
    public DataAlreadyExists(String message){
        super(message);
    }
}
