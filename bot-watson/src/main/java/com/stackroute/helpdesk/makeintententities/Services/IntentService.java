package com.stackroute.helpdesk.makeintententities.Services;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@NoArgsConstructor
public class IntentService implements IntentServiceInterface {
    private Assistant assistant;
    private  String workSpaceId;

    // initializer block to get workspace and assistant
    {

        IamOptions iamoptions = new IamOptions.Builder()
                .apiKey("<WATSON_API_KEY>")
                .build();
        Assistant assistant = new Assistant("2019-09-23", iamoptions);
        assistant.setEndPoint("https://gateway-lon.watsonplatform.net/assistant/api");
        this.assistant=assistant;

        ListWorkspacesOptions options = new ListWorkspacesOptions.Builder().build();

        WorkspaceCollection workspaces = assistant.listWorkspaces(options).execute().getResult();
        List<Workspace> workspaceList= workspaces.getWorkspaces();
        this.workSpaceId= workspaceList.get(0).getWorkspaceId();

    }


    public String createIntent(String name,List<String> testStrings){
        List<Example> examples = new ArrayList<>();
        for(String testString:testStrings)
            examples.add(new Example.Builder(testString).build());


        CreateIntentOptions options = new CreateIntentOptions.Builder(workSpaceId, name)
                .examples(examples)
                .build();

        Intent response = assistant.createIntent(options).execute().getResult();
        return  response.getIntent();



    }
    public void deleteIntent(){
        ListIntentsOptions options = new ListIntentsOptions.Builder(workSpaceId).build();

        IntentCollection response = assistant.listIntents(options).execute().getResult();
    for(Intent intent:response.getIntents()){
        DeleteIntentOptions options1 = new DeleteIntentOptions.Builder(workSpaceId, intent.getIntent()).build();

        assistant.deleteIntent(options1).execute();
    }}

}
