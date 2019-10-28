package com.stackroute.helpdesk.nointentnocommand.repository;

import com.stackroute.helpdesk.nointentnocommand.model.Report;

import org.json.simple.JSONObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, String>{

}
