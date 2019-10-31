
package com.stackroute.helpdesk.commandregistry.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String path;
//    private int status;
//    private String path;



}
