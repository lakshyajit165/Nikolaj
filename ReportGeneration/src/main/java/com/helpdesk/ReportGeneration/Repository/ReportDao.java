package com.helpdesk.ReportGeneration.Repository;

import com.helpdesk.ReportGeneration.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReportDao extends MongoRepository<Report, String> {

    @Query("{ 'createdOn' : { $gt: ?0 , '$lt': ?1 } }")
    List<Report> findAllByCreatedOn(Date start, Date end);

    Page<Report> findAllByTicketStatus(String status, Pageable pageable);

    Report findByTicketId(String id);

    List<Report> findAllByTicketStatus(String status);

    @Query("{ '$expr': { '$eq': [{ '$month': '$createdOn' }, ?0] } }")
    List<Report> findByCreatedOn(Integer month);

}