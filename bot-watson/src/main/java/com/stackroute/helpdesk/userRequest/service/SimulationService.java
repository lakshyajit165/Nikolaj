package com.stackroute.helpdesk.userRequest.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;
import com.stackroute.helpdesk.intentcommandmapping.service.Neo4jServiceRepo;
import com.stackroute.helpdesk.nointentnocommand.model.Report;
import com.stackroute.helpdesk.nointentnocommand.model.ReportType;
import com.stackroute.helpdesk.nointentnocommand.service.ReportServiceRepo;
import com.stackroute.helpdesk.ticketservice.model.Status;
import com.stackroute.helpdesk.ticketservice.model.TicketModel;
import com.stackroute.helpdesk.ticketservice.model.Type;
import com.stackroute.helpdesk.userRequest.model.ChatMessage;
import com.stackroute.helpdesk.userRequest.model.SuggestionsModel;
import com.stackroute.helpdesk.userRequest.repo.SuggestionsRepo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@Service
public class  SimulationService implements ChatServiceInterface {

    private Assistant assistant;
    private String workSpaceId;
    private String mongoId;
    private String ticketId;
    private MessageResponse response;
    private ChatMessage userRequest;
    private String responseFromCommand = "";

    @Autowired
    private SuggestionsRepo suggestionsRepo;
    @Autowired
    private Neo4jServiceRepo neo4jService;
    @Autowired
    private ReportServiceRepo reportService;


    //initializer block-execute only once
    {
        //creating session
        IamOptions iamoptions = new IamOptions.Builder()
                .apiKey("-Iu3aehg_g_ghAgLemP9nNRD71OxlPOWoSO6WBewKjxy")
                .build();
        Assistant assistant = new Assistant("2019-09-23", iamoptions);
        assistant.setEndPoint("https://gateway-lon.watsonplatform.net/assistant/api");
        this.assistant = assistant;

        ListWorkspacesOptions options = new ListWorkspacesOptions.Builder().build();

        WorkspaceCollection workspaces = assistant.listWorkspaces(options).execute().getResult();
        List<Workspace> workspaceList = workspaces.getWorkspaces();
        this.workSpaceId = workspaceList.get(0).getWorkspaceId();
    }

    //FUNCTION TO HANDLE QUERIES
    @Override
    public String postQuery(ChatMessage userRequest) {
        this.userRequest = userRequest;
        String responseFromBot = null;
        List<String> intents = new LinkedList<>();
        List<String> entities = new LinkedList<>();
        try {
            String userMessage = (String) userRequest.getContent();
            MessageInput input = new MessageInput();
            input.setText(userMessage);
            MessageOptions options = new MessageOptions.Builder(workSpaceId)
                    .input(input)
                    .build();
            MessageResponse response = assistant.message(options).execute().getResult();
            this.response = response;
            List<RuntimeIntent> responseIntents = response.getIntents();
            List<RuntimeEntity> responseEntities = response.getEntities();

            for (int i = 0; i < responseIntents.size(); i++)
                intents.add(responseIntents.get(i).getIntent());

            for (int i = 0; i < responseEntities.size(); i++)
                entities.add(responseEntities.get(i).getEntity());

            responseFromBot = response.getOutput().getGeneric().get(0).getText();


            //calling generate ticket function(should be called only once and should not be greeting message")
            //if (!((responseIntents.size()!=0)&&(responseIntents.get(0).getIntent().equals("greetings")))){

            //Calling no intent function
            if (responseIntents.size() == 0) {
                System.out.println("inside calling no intent");
                String entity = responseEntities.size() == 0 ? "" : responseEntities.get(0).getEntity();
                noIntentFound(userMessage, entity);
                ticketGenerate("open");
            } else {
                String entity = responseEntities.size() == 0 ? "" : responseEntities.get(0).getEntity();
                findCommands(userMessage, responseIntents.get(0), entity);
            }

            //Calling conversation end function
//            if (responseFromBot.equals("Thank you for your rating")) {
//                //endConversation();
//                //updateTicket();
//            }

            if(!(this.responseFromCommand.equals("")))
                responseFromBot = this.responseFromCommand;

            this.responseFromCommand="";
            //System.out.println(response);
//            Map<String, Object> map = new TreeMap<>();
//            map.put("status", HttpStatus.OK);
//            map.put("data", response);
//            map.put("message", (response == null) ? "No Response" : "Got Response");

        } catch (Exception e) {
            System.out.println("Caught exception " + e);
        }
        return responseFromBot;
    }

    //Function to update confidence
    public void updateConfidence() {
    }

    public void ticketGenerate(String status){

        List<RuntimeIntent> responseIntents = response.getIntents();
        List<RuntimeEntity> responseEntities = response.getEntities();
        String userMessage = userRequest.getContent();
        List<String> intents = new LinkedList<>();
        for (int i = 0; i < responseIntents.size(); i++)
            intents.add(responseIntents.get(i).getIntent());
        if (((responseIntents.size() != 0) && (!(responseIntents.get(0).getIntent().equals("Greetings")))) || (responseIntents.size()==0)) {
            System.out.println("Inside ticket creation in chat service");
            TicketModel ticketModel = new TicketModel();
            ticketModel.setRaisedBy(userRequest.getEmailId());
            ticketModel.setAssignedTo("bot");
            if (responseEntities.size() != 0)
                ticketModel.setEntity(responseEntities.get(0).getEntity());
            ticketModel.setQuery(userMessage);
            ticketModel.setType(Type.query);
            ticketModel.setStatus(Status.valueOf(status));
            ticketModel.setIntent(intents);
            final String uri = "https://nikolaj-dev.stackroute.io/ticket-service/api/v1/tickets";
            //final String uri = "http://15.206.36.205:8765/ticket-service/api/v1/tickets";
            //final String uri = "http://localhost:8765/ticket-service/api/v1/tickets";
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<TicketModel> request = new HttpEntity<>(ticketModel);
            ResponseEntity<LinkedHashMap> map = restTemplate.postForEntity(uri, request, LinkedHashMap.class);
            System.out.println(map);
            System.out.println("database inserted");
            ObjectMapper mapper = new ObjectMapper();
            TicketModel ticket = mapper.convertValue(map.getBody().get("result"), TicketModel.class);
            ticketId = ticket.getUuid().toString();
            System.out.println("ticket id inside post query " + ticketId);
        }

    }

    //Function to update ticket status once its resolved by bot
//    @Override
//    public TicketModel updateTicket() {
//        Optional<TicketModel> ticket = ticketRepo.findById(mongoId);
//        System.out.println("closing ticket " + ticket.get());
//        TicketModel newTicket = ticket.get();
//        newTicket.setStatus(TicketModel.Status.closed);
//        System.out.println(newTicket);
//        ticketRepo.save(newTicket);
//        return newTicket;
//    }

    //Function to find no intent
    public void noIntentFound(String userMessage, String entity) {
        Report reportModel = new Report();
        reportModel.setTicketName(userMessage);
        reportModel.setEntity(entity);
        reportModel.setTicketId(ticketId);
        reportModel.setReportType(ReportType.NoIntent);
        reportModel.setUserId("abc@gmail.com");
        reportModel.setCreatedOn(new Date());
        reportModel.setUpdatedOn(new Date());
        reportService.addRecord(reportModel);
    }

    //Function to end conversation(delete session)
//    public void endConversation() {
//        try {
//            DeleteSessionOptions delete_options = new DeleteSessionOptions.Builder("c8a2848b-776d-4773-8d73-1e9c6527183d", sessionId).build();
//            assistant.deleteSession(delete_options).execute();
//            System.out.println("Conversation ended.....");
//        } catch (Exception e) {
//            System.out.println("Resource not found exception");
//        }
//
//    }

    //Function to connect to customer representative
    public void connectToCsr() {
        System.out.println("Connecting to csr......");
    }

    //Function to suggest actions to csr through redis database
    public void findCommands(String userMessage, RuntimeIntent intent, String entity) {
        try {
            String intentName = intent.getIntent();
            System.out.println("In suggestions");
            List<JSONObject> suggestionsList = neo4jService.getCommandByName(intentName, "");
            //no command report
            System.out.println(suggestionsList);
            if (suggestionsList == null) {
                ticketGenerate("open");
                noCommandFound(userMessage, intentName, entity);
            }
            //creating suggestions
            else {
                if ((Long) suggestionsList.get(0).get("Confidence") > 90) {
                    ticketGenerate("closed");
                    //execute()
//                    String suggestions = (String) suggestionsList.get(0).get("Command name");
//                    System.out.println("suggestions variable "+suggestions);
//                    String url = "https://nikolaj-dev.stackroute.io/commandregistry /api/v1/commandregistry/execute/"+suggestions;
//                    RestTemplate restTemplate = new RestTemplate();
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("csrUserId", "lk@gmail.com");
//                    HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject);
//                    ResponseEntity<LinkedHashMap> map = restTemplate.postForEntity(url, request , LinkedHashMap.class);
//                    System.out.println("Map variable "+map);
//                    List<String> listCommand = (List<String>) map.getBody().get("result");
//                    System.out.println(" List command variable "+listCommand);
//                    for (String commandResponse : listCommand)
//                        this.responseFromCommand += commandResponse;
                } else {
                    ticketGenerate("open");
                    String suggestions = (String) suggestionsList.get(0).get("Command name");
                    if (!suggestions.equals("")) {
                        SuggestionsModel new_suggestion_model = new SuggestionsModel();
                        new_suggestion_model.setId(ticketId);
                        new_suggestion_model.setSuggestion(suggestions);
                        suggestionsRepo.save(new_suggestion_model);
                        System.out.println(suggestions);
                        System.out.println(new_suggestion_model);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("caught " + e);
        }

    }

    public void noCommandFound(String userMessage, String intent, String entity) {
        Report reportModel = new Report();
        reportModel.setTicketName(userMessage);
        reportModel.setIntent(intent);
        reportModel.setEntity(entity);
        reportModel.setTicketId(ticketId);
        reportModel.setReportType(ReportType.NoCommand);
        reportModel.setUserId("abc@gmail.com");
        reportModel.setCreatedOn(new Date());
        reportModel.setUpdatedOn(new Date());
        reportService.addRecord(reportModel);
    }


    @Override
    public SuggestionsModel getSuggestions(String id) {
        SuggestionsModel newSuggestion = new SuggestionsModel();
        // try{
        Optional<SuggestionsModel> suggestions = suggestionsRepo.findById(id);

        System.out.println(suggestions);
        newSuggestion = suggestions.get();

        //}
//        catch(Exception e){}
        return newSuggestion;
    }

}