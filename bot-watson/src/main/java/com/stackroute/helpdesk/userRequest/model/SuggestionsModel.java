package com.stackroute.helpdesk.userRequest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("Ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionsModel {
    private String id;
    private String suggestion;
//    private List<String> commandParameter;
}
