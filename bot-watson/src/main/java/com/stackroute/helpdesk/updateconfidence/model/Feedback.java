package com.stackroute.helpdesk.updateconfidence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    String intentName;
    String commandName;
    Integer rating;
}
