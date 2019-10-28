//package com.stackroute.helpdesk.userRequest.service;
//
//import com.stackroute.helpdesk.userRequest.model.ChatMessage;
//import com.stackroute.helpdesk.userRequest.model.TicketModel;
//import com.stackroute.helpdesk.userRequest.repo.TicketRepo;
//import org.bson.types.ObjectId;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Map;
//import java.util.TreeMap;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//
//public class TestChatService {
//
//    @InjectMocks
//    ChatService chatService;
//    @Mock
//    TicketRepo ticketRepo;
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    //testing post query method in service
//    @Test
//    public void postQueryResponseTest1(){
//        String message = "Welcome to Optimus, How can I help you";
//        ChatMessage testMessage = new ChatMessage();
//        testMessage.setContent("");
//        assertEquals(message,chatService.postQuery(testMessage));
//    }
//    @Test
//    public void postQueryResponseTest2(){
//        String message = "Hello, How can I help you";
//        ChatMessage testMessage = new ChatMessage();
//        testMessage.setContent("Hi");
//        assertEquals(message,chatService.postQuery(testMessage));
//    }
//    //testing add ticket method in service
//    @Test
//    public void addTicketResponseTest1(){
//        TicketModel ticketModel= new TicketModel();
//        ObjectId id = new ObjectId();
//        ticketModel.set_id(id);
//        chatService.addTicket(ticketModel);
//        verify(ticketRepo, times(1)).save(ticketModel);
//    }
//    //testing add ticket method in service
//    @Test
//    public void addTicketResponseTest2(){
//        TicketModel actual_ticketModel= new TicketModel();
//        TicketModel new_ticketModel= new TicketModel();
//        ObjectId id = new ObjectId();
//        actual_ticketModel.set_id(id);
//        new_ticketModel.set_id(id);
//        when(ticketRepo.save(actual_ticketModel)).thenReturn(new_ticketModel);
//        assertEquals(new_ticketModel, chatService.addTicket(actual_ticketModel));
//
//    }
//}
