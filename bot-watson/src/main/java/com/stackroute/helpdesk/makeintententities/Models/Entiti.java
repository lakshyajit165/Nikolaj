package com.stackroute.helpdesk.makeintententities.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class Entiti {
    String name;
    Map<String,List<String>> values;
}
