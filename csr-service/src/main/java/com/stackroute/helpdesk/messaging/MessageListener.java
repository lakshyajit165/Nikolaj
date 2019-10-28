package com.stackroute.helpdesk.messaging;

//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.Queue;
//
//@Component
//public class MessageListener{
//
//    public static Queue<LinkedHashMap> ticketToBeAssigned = new LinkedList<>();
//    public static Queue<LinkedHashMap> csrToBeUpdated = new LinkedList<>();
//
//    @RabbitListener(queues="csr-requested-queue-subscribe")
//    public void assignTicket(MessageStructure receivedMessage) throws Exception{
//
//        System.out.println(receivedMessage);
//
//        //LinkedHashMap messageStructure = (LinkedHashMap) receivedMessage.getEventData();
//        ticketToBeAssigned.add((LinkedHashMap) receivedMessage.getEventData());
//        //System.out.println(messageStructure);
//        // System.out.println(((LinkedHashMap)messageStructure.get("result")).get("id"));
//    }
//
//    @RabbitListener(queues="csr-updated-queue-subscribe")
//    public void updateCsr(MessageStructure receivedMessage) throws Exception{
//
//        csrToBeUpdated.add((LinkedHashMap) receivedMessage.getEventData());
//
//    }
//
//    public static LinkedHashMap assignTicket(){
//        return ticketToBeAssigned.remove();
//    }
//
//    public static LinkedHashMap updateCsr(){
//        return csrToBeUpdated.remove();
//    }
//
//
//
//}