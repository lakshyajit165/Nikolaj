package com.stackroute.helpdesk.commandregistry.getbasiccommands;


import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicCommandsTest {

    private MockMvc mockMvcBasicCommands;

    @InjectMocks
    private BasicCommands basicCommands;

    @Before
    public void setUp() throws Exception {
        mockMvcBasicCommands = MockMvcBuilders.standaloneSetup(basicCommands)
                .build();
    }

    @Mock
    private TestRestTemplate restTemplate;


    @Test
    public void getBasicCommands() throws Exception {

        HashMap<String, Object> responseObject = new HashMap<>();
        responseObject.put("result", "generic helpdesk");
        responseObject.put("message", "success");
        responseObject.put("error", "false");
        Mockito
                .when(restTemplate.getForObject("http://zuul-api-gateway:8765/command-framework/basic-commands", Object.class))
                .thenReturn(new ResponseEntity<HashMap<String, Object>>(responseObject, HttpStatus.OK));

        Object result = restTemplate.getForObject("http://zuul-api-gateway:8765/command-framework/basic-commands", Object.class);
        String expected = result.toString().split(",")[0];
        assertEquals(expected.split(" ")[1], "OK");
    }
}
