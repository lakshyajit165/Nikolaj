package com.stackroute.helpdesk.nointentnocommand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReport {
    String ticketId;
    String entity;
    String intent;
    String command;
}
