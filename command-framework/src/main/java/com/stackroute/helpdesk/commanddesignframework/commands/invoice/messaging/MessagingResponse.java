package com.stackroute.helpdesk.commanddesignframework.commands.invoice.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagingResponse {
    String eventName;
    Object eventData;
    String exchangeName;
    String routingKey;
    String bindingQueue;
//    public MessagingResponse(
//            @JsonProperty("eventName")  String eventName,
//            @JsonProperty("eventData") Object eventData,
//            @JsonProperty("exchangeName") String exchangeName,
//            @JsonProperty("routingKey")  String routingKey,
//            @JsonProperty("bindingQueue") String bindingQueue
//    ) {
//        this.eventName = eventName;
//        this.eventData = eventData;
//        this.exchangeName = exchangeName;
//        this.routingKey = routingKey;
//        this.bindingQueue = bindingQueue;
//    }
}
