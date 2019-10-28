package com.stackroute.helpdesk.intentcommandmapping.exceptionhandler;

import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.CommandParameterException;
import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.DataAlreadyExists;
import com.stackroute.helpdesk.intentcommandmapping.exceptionclass.ParameterException;
import com.stackroute.helpdesk.intentcommandmapping.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionHandling {
    @ExceptionHandler(ParameterException.class)
    public final ResponseEntity<Object> handleIntentParameterException(ParameterException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataAlreadyExists.class)
    public final ResponseEntity<Object> handleUpdateIntentException(DataAlreadyExists ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CommandParameterException.class)
    public final ResponseEntity<Object> handleCommandParameterException(CommandParameterException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }


}
