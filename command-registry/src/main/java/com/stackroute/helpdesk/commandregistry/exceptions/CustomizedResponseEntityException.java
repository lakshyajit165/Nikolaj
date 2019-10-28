package com.stackroute.helpdesk.commandregistry.exceptions;//package com.stackroute.helpdesk.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {

    private ResponseEntity<HashMap<String,Object>> responseObject;
    private HashMap<String,Object> responseHashMap;

    @ExceptionHandler(CommandNotFoundException.class)
    public final ResponseEntity<HashMap<String,Object>> handleTrackNotFoundException(Exception commandNotFoundException, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),commandNotFoundException.getMessage(),request.getDescription(false));
        responseHashMap = new HashMap<>();
        responseHashMap.put("error","true");
        responseHashMap.put("message","Error occured");
        responseHashMap.put("result",exceptionResponse);
        return new ResponseEntity(responseHashMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommandAlreadyExistsException.class)
    public final ResponseEntity<HashMap<String,Object>> handleTrackNotFoundException(CommandAlreadyExistsException commandAlreadyExistsException, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),commandAlreadyExistsException.getMessage(),request.getDescription(false));
        responseHashMap = new HashMap<>();
        responseHashMap.put("error","true");
        responseHashMap.put("message","Error occured");
        responseHashMap.put("result",exceptionResponse);
        return new ResponseEntity(responseHashMap, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(ParameterListNotValid.class)
    public final ResponseEntity<HashMap<String,Object>> handleTrackNotFoundException(ParameterListNotValid parameterListNotValid, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),parameterListNotValid.getMessage(),request.getDescription(false));
        responseHashMap = new HashMap<>();
        responseHashMap.put("error","true");
        responseHashMap.put("message","Error occured");
        responseHashMap.put("result",exceptionResponse);
        return new ResponseEntity(responseHashMap, HttpStatus.ALREADY_REPORTED);
    }

}
