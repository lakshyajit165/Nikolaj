package com.stackroute.helpdesk.mailservice.messaging;

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
    Object eventData;
    String exchangeName;
    String routingKey;
    String bindingQueue;
}
