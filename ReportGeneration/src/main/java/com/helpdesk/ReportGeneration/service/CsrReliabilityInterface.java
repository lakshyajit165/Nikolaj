package com.helpdesk.ReportGeneration.service;

import com.helpdesk.ReportGeneration.entity.Csr;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.HashMap;

public interface CsrReliabilityInterface {

    public HashMap<String, Csr> putHashMap();
    public void getMonth(Integer month);

}