//
//package com.stackroute.helpdesk;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.stackroute.helpdesk.controller.CommandRegistryController;
//import com.stackroute.helpdesk.entity.Commands;
//import com.stackroute.helpdesk.entity.Parameters;
//import com.stackroute.helpdesk.entity.Status;
//import com.stackroute.helpdesk.repository.CommandRepository;
//import com.stackroute.helpdesk.service.CommandInterface;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import org.skyscreamer.jsonassert.JSONAssert;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = CommandRegistryController.class)
//
//public class CommandRegistryControllerTest {
//    Commands mockCommands;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private CommandRegistryController commandRegistryController;
//
//    private HashMap<String, Object> commandList;
//    private ResponseEntity<HashMap<String, Object>> response;
//
//    @MockBean
//    private CommandRepository commandRepository;
//    @MockBean
//    private CommandInterface commandInterface;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//    List<Parameters> parametersList = new ArrayList<>();
//    @Test
//    public void getCommands() throws Exception {
//        String str[] = {"invoice", "payment"};
//        commandList = new HashMap<>();
//        mockCommands = new Commands("1","add",str,"adding new command",new Date(),new Date(),"divya","syntax", Status.ACTIVE.toString(),parametersList);
//        commandList.put("result", mockCommands);
//        commandList.put("errors", false);
//        commandList.put("message", "Success");
//        response = new ResponseEntity<>(commandList, HttpStatus.OK);
//        mockMvc.perform(get("/api/v1/command-registry/commands")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(mockCommands)))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }
//
//    private static String jsonToString(final Object ob) throws JsonProcessingException {
//        String result;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(ob);
//            result = jsonContent;
//        } catch (JsonProcessingException e) {
//            result = "JSON processing error";
//        }
//        return result;
//    }
//}
//
