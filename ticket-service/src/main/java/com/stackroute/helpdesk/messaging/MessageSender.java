package com.stackroute.helpdesk.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    public void sendMessage(
            RabbitTemplate rabbitTemplate,
            String eventName,
            String exchange,
            String routingKey,
            Object data,
            String bindingQueue
    ) {

//        SimpleMessageConverter converter = new SimpleMessageConverter();
        // rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());


        MessageStructure messageStructure = new MessageStructure(
                eventName,
                data,
                exchange,
                routingKey,
                bindingQueue
        );

        rabbitTemplate.convertAndSend(exchange, routingKey, messageStructure );

         System.out.println("The message has been sent to the queue. in the queue = " + bindingQueue + " routing key = " + routingKey);
    }
}