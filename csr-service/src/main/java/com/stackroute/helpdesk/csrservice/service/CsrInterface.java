package com.stackroute.helpdesk.csrservice.service;

import com.stackroute.helpdesk.csrservice.entity.CsrStructure;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.UUID;

public interface CsrInterface {

    public List<CsrStructure> getAllCsr();

    public void saveCsr(CsrStructure csrStructure);

    public CsrStructure getCsrById(String id);

    public JSONObject noOfTickets(String email, int status);



}
