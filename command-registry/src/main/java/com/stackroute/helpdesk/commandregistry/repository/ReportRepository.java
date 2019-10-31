package com.stackroute.helpdesk.commandregistry.repository;;
import com.stackroute.helpdesk.commandregistry.entity.ReportDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ReportRepository extends MongoRepository<ReportDetails,String> {
	List<ReportDetails> findByTypeOfReport(String type);
}