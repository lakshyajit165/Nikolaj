package com.stackroute.helpdesk.commandregistry.invoker;

//import com.rabbitmq.client.ConnectionFactory;
import com.stackroute.helpdesk.commandregistry.exceptions.CommandNotFoundException;
import com.stackroute.helpdesk.commandregistry.exceptions.ParameterListNotValid;
import com.stackroute.helpdesk.commandregistry.invoker.model.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/commandregistry")
public class Invoker {

    @Value("${API_GATEWAY_HOST_PORT}")
    private String hostName;

    static String paramString = "";
    private HashMap<String,Object> responseEntityBody;
    private RestTemplate restTemplate;

    @PostMapping("/execute/{commandString}")
    public ResponseEntity<HashMap<String, Object>> execute(
            @PathVariable("commandString") String commandString,
            @RequestParam(value = "email", required = false) String sendToEmail,
            @RequestBody Context context) throws Exception {
        boolean sendResponseToEmail = false;
        if(sendToEmail == null || sendToEmail.equalsIgnoreCase("false")){
            sendResponseToEmail = false;
        } else if(sendToEmail.equalsIgnoreCase("true")) {
            sendResponseToEmail = true;
        }
        restTemplate = new RestTemplate();
        String[] command_string = commandString.split(" ");
        String command = command_string[0];
        paramString = "param0="+sendToEmail+"&";
        String parameters = "";
        if(command_string.length > 1) {
            parameters = createParameter(command_string[1]);
            paramString = "";
        }
        String commandExecution = hostName+"commandframework/"+command+"?"+parameters;
        System.out.println("command executed in command framework = "+commandExecution);
        try{
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(commandExecution, Object.class);
                return generatingResponseStructure(responseEntity, context, "Success", "false");
        }catch (HttpClientErrorException.NotFound notFoundException){
            throw new CommandNotFoundException("command not found");
        }catch (HttpClientErrorException.BadRequest badRequestException){
            throw new ParameterListNotValid("not enough parameters");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public String createParameter(String parameterList){
       String[] listOfParameters = parameterList.split(",");
        AtomicInteger index = new AtomicInteger(0);
        Arrays.asList(listOfParameters).stream().forEach((parameter) -> {
            paramString += "param"+ (index.get()+1) +"="+listOfParameters[index.getAndIncrement()]+"&";
        });
        return paramString;
    }

    public ResponseEntity<HashMap<String, Object>> generatingResponseStructure(ResponseEntity<Object> responseEntity, Context context, String message, String error){
        Object result = (Object) responseEntity.getBody();
        responseEntityBody = new HashMap<>();
        responseEntityBody.put("result", result);
        responseEntityBody.put("message", message);
        responseEntityBody.put("error", error);
        responseEntityBody.put("context", context);
        return new ResponseEntity<>(responseEntityBody, HttpStatus.OK);
    }
}
