package com.stackroute.helpdesk.commandregistry.entity;

import lombok.Data;

import java.util.List;
@Data
public class Intent {
    private String name;
    List<String> testStrings;
}
