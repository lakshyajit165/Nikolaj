package com.stackroute.helpdesk.updateconfidence.exceptionhandler;

import com.stackroute.helpdesk.updateconfidence.exceptionclass.NoMatchFound;
import com.stackroute.helpdesk.updateconfidence.exceptionclass.UrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class UpdateConfidenceExceptionHandling {

    @ExceptionHandler(UrlException.class)
    public final ResponseEntity<Object> handleIntentParameterException(UrlException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoMatchFound.class)
    public final ResponseEntity<Object> handleIntentParameterException(NoMatchFound ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
