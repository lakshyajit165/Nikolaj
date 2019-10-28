package com.stackroute.helpdesk.makeintententities.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor

public class Intent {
    String name;
    List<String> testStrings;

    @Override
    public String toString() {
        return "Intent{" +
                "name='" + name + '\'' +
                ", testStrings=" + testStrings +
                '}';
    }
}
