package com.stackroute.helpdesk.commanddesignframework.commands;

import com.stackroute.helpdesk.commanddesignframework.commands.refund.controller.RefundController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RefundTest {

    private MockMvc mockMvcRefund;

    @InjectMocks
    private RefundController refund;

    @Before
    public void setUp() throws Exception{
        mockMvcRefund = MockMvcBuilders.standaloneSetup(refund)
                .build();
    }

    @Test
    public void initiateRefund() throws Exception {
        mockMvcRefund.perform(
                MockMvcRequestBuilders.get("/refund?param0=1&param1=2")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result").value("your refund is inititated for userId= 1"));
    }
}
