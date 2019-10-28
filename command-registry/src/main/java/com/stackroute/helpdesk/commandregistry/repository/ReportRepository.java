package com.stackroute.helpdesk.commandregistry.repository;;
import com.stackroute.helpdesk.commandregistry.commandstorage.model.ReportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository  extends MongoRepository<ReportDetails,String> {
}
