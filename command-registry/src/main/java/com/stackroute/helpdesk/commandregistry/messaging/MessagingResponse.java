package com.stackroute.helpdesk.commandregistry.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessagingResponse {
    String eventName;
    String eventData;
    String exchangeName;
    String routingKey;
    String bindingQueue;
}
