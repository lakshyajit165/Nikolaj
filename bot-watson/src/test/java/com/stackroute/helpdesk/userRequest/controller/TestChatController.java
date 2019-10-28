//package com.stackroute.helpdesk.userRequest.controller;
//
//import com.stackroute.helpdesk.userRequest.controller.ChatController;
//import com.stackroute.helpdesk.userRequest.model.ChatMessage;
//import com.stackroute.helpdesk.userRequest.service.ChatService;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class TestChatController {
//
//    @InjectMocks
//    ChatController chatController;
//    @Mock
//    ChatService chatService;
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSendMessage(){
//        ChatMessage testMessage = new ChatMessage();
//        assertEquals(chatController.sendMessage(testMessage),testMessage);
//    }
//
//    @Test
//    public void testBotSendMessage(){
//        ChatMessage testMessage = new ChatMessage();
//        chatController.botSendMessage(testMessage);
//        verify(chatService , times(1)).postQuery(testMessage);
//    }
//
//
//}
