package com.stackroute.helpdesk.userRequest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Ticket")
@Data
@AllArgsConstructor
public class SuggestionsModel {
    private String id;
    private String suggestion;
}
