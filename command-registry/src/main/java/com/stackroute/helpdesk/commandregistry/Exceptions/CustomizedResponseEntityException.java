//package com.stackroute.helpdesk.Exceptions;
//
//
//import com.stackroute.helpdesk.commandregistry.Exceptions.CommandNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import java.util.Date;
//@ControllerAdvice
//@RestController
//public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(CommandNotFoundException.class)
//    public final ResponseEntity<Object> handleTrackNotFoundException(CommandNotFoundException ex, WebRequest request)
//    { ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
//        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(CommandAlreadyExistsException.class)
//    public final ResponseEntity<Object> handleTrackNotFoundException(CommandAlreadyExistsException ex, WebRequest request)
//    { ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
//        return new ResponseEntity(exceptionResponse, HttpStatus.ALREADY_REPORTED);
//    }
//
//
//    private class ExceptionResponse {
//        public ExceptionResponse(Date date, String message, String description) {
//        }
//    }
//}
