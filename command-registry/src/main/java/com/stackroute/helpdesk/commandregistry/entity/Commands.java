package com.stackroute.helpdesk.commandregistry.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

//import javax.annotation.processing.Generated;


//import javax.annotation.processing.Generated;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Document(collection = "command")
public class Commands
{
    @Id
    private String id;

    @NotBlank
    @Indexed(unique=true)
    private String name;
    private String []tags;
    private String desc;
    private Date last_updated_on;
    private Date created_on;
    private String created_by;
    private String usage;
    private String status;


    private List<Parameters> parametersList;



}




