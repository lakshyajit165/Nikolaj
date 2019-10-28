package com.stackroute.helpdesk.makeintententities.Services;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.*;
import com.stackroute.helpdesk.makeintententities.Models.Entiti;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EntityService {


    private Assistant assistant;
    private  String workSpaceId;

    // initializer block to get workspace and assistant
    {

        IamOptions iamoptions = new IamOptions.Builder()
                .apiKey("-Iu3aehg_g_ghAgLemP9nNRD71OxlPOWoSO6WBewKjxy")
                .build();
        Assistant assistant = new Assistant("2019-09-23", iamoptions);
        assistant.setEndPoint("https://gateway-lon.watsonplatform.net/assistant/api");
        this.assistant=assistant;

        ListWorkspacesOptions options = new ListWorkspacesOptions.Builder().build();

        WorkspaceCollection workspaces = assistant.listWorkspaces(options).execute().getResult();
        List<Workspace> workspaceList= workspaces.getWorkspaces();
        this.workSpaceId= workspaceList.get(0).getWorkspaceId();

    }

    public String createEntity(Entiti entity){
        List<CreateValue> entityValues = new ArrayList<CreateValue>();
        Set<String> keys=entity.getValues().keySet();
        for(String key:keys){
            List<String> synonyms=entity.getValues().get(key);

                entityValues.add(new CreateValue.Builder(key).synonyms(synonyms).build());

        }


        CreateEntityOptions options = new CreateEntityOptions.Builder(workSpaceId, entity.getName())
                .values(entityValues)
                .build();

        Entity response = assistant.createEntity(options).execute().getResult();
        return response.getEntity();

    }




}
