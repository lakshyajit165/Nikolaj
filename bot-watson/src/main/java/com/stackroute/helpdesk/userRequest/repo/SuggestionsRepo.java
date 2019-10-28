package com.stackroute.helpdesk.userRequest.repo;

import com.stackroute.helpdesk.userRequest.model.SuggestionsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionsRepo extends CrudRepository<SuggestionsModel, String> {

}
