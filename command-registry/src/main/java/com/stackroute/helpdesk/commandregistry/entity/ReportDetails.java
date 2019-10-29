package com.stackroute.helpdesk.commandregistry.entity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "reports")
public class ReportDetails {
    @Id
    String id;
    String typeOfReport;
    String entity;
    String intent;
    List<Intent> intentList;
    public ReportDetails(String typeOfReport, String entity, String intent, List<Intent> intentList) {
        this.typeOfReport = typeOfReport;
        this.entity = entity;
        this.intent = intent;
        this.intentList = intentList;
    }
}