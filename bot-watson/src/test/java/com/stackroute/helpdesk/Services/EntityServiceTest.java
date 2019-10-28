package com.stackroute.helpdesk.Services;

import com.stackroute.helpdesk.makeintententities.Models.Entiti;
import com.stackroute.helpdesk.makeintententities.Services.EntityService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityServiceTest {
    //@Autowired
    EntityService entityService =new EntityService();
    @Test
    public void createEntityTest() {
        Entiti entiti =new Entiti();
        entiti.setName("entity1");
        Map<String, List<String>> map=new HashMap<>();
        ArrayList<String> list=new ArrayList<>();
        list.add("aa");
        list.add("ab");
        map.put("aab",list);
        entiti.setValues(map);
        String actual=entityService.createEntity(entiti);
        assertEquals(entiti.getName(),actual);
    }
}