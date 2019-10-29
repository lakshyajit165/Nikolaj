package com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.service;

import com.stackroute.helpdesk.commanddesignframework.commands.advanceorder.model.AdvanceOrder;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class AdvanceOrderService {
    private List<String> advanceOrderList;
    public List<String> getAdvanceOrderList(JSONObject jsonObject) {
        List<AdvanceOrder> advanceOrder = (List<AdvanceOrder>) jsonObject.get("data");
        advanceOrderList = new ArrayList<>();
        Arrays.stream(advanceOrder.toArray()).forEach((advanceOrders) -> {
            advanceOrderList.add(getResult((LinkedHashMap) advanceOrders));
        });
        return advanceOrderList;
    }

    public String getResult(LinkedHashMap advanceOrderList) {
        return "Advance Order On " +advanceOrderList.get("order_id")+ "is "+advanceOrderList.get("name")  ;
    }
}



