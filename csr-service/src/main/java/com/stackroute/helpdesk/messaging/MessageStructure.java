package com.stackroute.helpdesk.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageStructure implements Serializable {
    String eventName;
    Object eventData;
    String exchangeName;
    String routingKey;
    String bindingQueue;

    public MessageStructure(
            @JsonProperty("eventName")  String eventName,
            @JsonProperty("eventData") Object eventData,
            @JsonProperty("exchangeName") String exchangeName,
            @JsonProperty("routingKey")  String routingKey,
            @JsonProperty("bindingQueue") String bindingQueue
    ) {
        this.eventName = eventName;
        this.eventData = eventData;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.bindingQueue = bindingQueue;
    }
}