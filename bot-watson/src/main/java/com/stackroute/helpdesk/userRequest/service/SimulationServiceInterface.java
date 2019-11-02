package com.stackroute.helpdesk.userRequest.service;

import com.stackroute.helpdesk.userRequest.model.ChatMessage;
import com.stackroute.helpdesk.userRequest.model.SuggestionsModel;

public interface SimulationServiceInterface {

    //Map<String, Object> postQuery(Map userRequest);
    String postQuery(ChatMessage userRequest);
    //Map<String, Object> addTicket(Map userRequest);
    //TicketModel getTicket(String id);
    SuggestionsModel getSuggestions(String id);
    //void noIntentFound(ChatMessage userRequest);
    //void endConversation();
    void connectToCsr();
}
