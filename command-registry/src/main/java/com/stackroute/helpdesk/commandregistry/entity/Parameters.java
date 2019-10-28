
package com.stackroute.helpdesk.commandregistry.entity;




import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Parameters {
    private String parameterName;
    private String required;
    private String default_value;
    private String description;
    private String value;



    public Parameters(String parameterName, String default_value, String description, String value,String required) {
        this.parameterName = parameterName;
        this.default_value = default_value;
        this.description = description;
        this.value = value;
        this.required=required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}
