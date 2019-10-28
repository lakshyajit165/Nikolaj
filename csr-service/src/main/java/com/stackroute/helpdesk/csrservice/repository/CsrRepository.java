package com.stackroute.helpdesk.csrservice.repository;

import com.stackroute.helpdesk.csrservice.entity.CsrStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CsrRepository extends MongoRepository<CsrStructure, String> {

    public CsrStructure findByUuid(String uuid);

    @Override
    public List<CsrStructure> findAll();
}
