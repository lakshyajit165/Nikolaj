package com.stackroute.helpdesk.commandregistry.invoker;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvokerTest {

    private MockMvc mockMvcInvoker;

//    @Autowired
//    private Invoker invoker;

    @MockBean
    private Invoker invoker;

    @Mock
    private TestRestTemplate restTemplate;


    @Before
    public void setUp() throws Exception{
        mockMvcInvoker = MockMvcBuilders.standaloneSetup(invoker)
                .build();
    }


    @Test
    public void execute() throws Exception {
        HashMap<String,Object> responseObject = new HashMap<>();
        responseObject.put("result","generic helpdesk");
        responseObject.put("message","success");
        responseObject.put("error","false");
        Mockito
                .when(restTemplate.getForObject("http://zuul-api-gateway:8765/command-framework/information", Object.class))
                .thenReturn(new ResponseEntity<HashMap<String,Object>>(responseObject, HttpStatus.OK));

        Object result = restTemplate.getForObject("http://zuul-api-gateway:8765/command-framework/information", Object.class);
        String expected = result.toString().split(",")[1];
        assertEquals(expected.substring(8),"generic helpdesk");
    }
}
