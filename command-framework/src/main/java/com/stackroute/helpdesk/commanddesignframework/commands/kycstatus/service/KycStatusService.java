package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.service;

import com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model.KycStatus;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class KycStatusService {

    public List<String> getKycStatus(JSONObject jsonObject){
        List<KycStatus> documentList = (List<KycStatus>) jsonObject.get("data");
        List<String> resultList = new ArrayList<>();
        Arrays.stream(documentList.toArray()).forEach((document) -> {
            resultList.add("Your KYC status is "+(String) ((LinkedHashMap) document).get("documentStatus") + ".");
        });
        return resultList;
    }
}
