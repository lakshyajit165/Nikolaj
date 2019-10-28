package com.helpdesk.ReportGeneration.Messaging.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessagingResponse {

    String eventName;
    Object eventData;
    String exchangeName;
    String routingKey;
    String bindingQueue;

}
