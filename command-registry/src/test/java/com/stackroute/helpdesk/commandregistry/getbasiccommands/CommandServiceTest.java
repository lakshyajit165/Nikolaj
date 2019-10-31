//package com.stackroute.helpdesk;
//import com.mongodb.event.CommandStartedEvent;
//import com.stackroute.helpdesk.controller.CommandRegistryController;
//import com.stackroute.helpdesk.entity.Commands;
//import com.stackroute.helpdesk.entity.Parameters;
//import com.stackroute.helpdesk.entity.Status;
//import com.stackroute.helpdesk.repository.CommandRepository;
//import com.stackroute.helpdesk.service.CommandService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CommandServiceTest {
//    @Autowired
//    private CommandService commandService;
//
//    @MockBean
//    private CommandRepository repository;
//
//    String[] str={"invoice","payment"};
//    List<Parameters> parametersList=new ArrayList<>();
//    @Test
//    public void getCommandsTest() throws Exception
//    {
//        when(repository.findAll()).thenReturn(Stream.of(
//                new Commands("add",str,"adding new command",new Date(),new Date(),"divya","syntax", Status.ACTIVE.toString(),parametersList),
//                new Commands("info",str,"finding info for command",new Date(),new Date(),"divya","syntax", Status.ACTIVE.toString(),parametersList))
//                .collect(Collectors.toList()));
//        assertEquals(2,commandService.getCommands().size());
//    }
//    @Test
//    public void addCommandsTest()throws Exception
//    {
//           Commands commands=new Commands("add",str,"adding new command",new Date(),new Date(),"divya","syntax", Status.ACTIVE.toString());
//        when(repository.save(commands)).thenReturn(commands);
//        assertEquals(commands, commandService.addCommands(commands));
//    }
//
////    @Test
////   public void patchCommandsTest() throws Exception
////    {
////      Commands commands=  new Commands("add",str,"adding new command",new Date(),new Date(),"divya","syntax", Status.ACTIVE.toString());
////        Mockito.when(repository.save(commands)).thenReturn(commands);
////		assertEquals(commands,commandService.patchCommands(new Commands("add",str,"adding new command",new Date(),new Date(),"divya","syntax", Status.ACTIVE.toString())));
////
////    }
//
//
//
//}
