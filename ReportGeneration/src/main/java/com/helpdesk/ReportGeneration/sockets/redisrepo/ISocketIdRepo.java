package com.helpdesk.ReportGeneration.sockets.redisrepo;

import com.helpdesk.ReportGeneration.sockets.model.SocketStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocketIdRepo extends CrudRepository<SocketStore, String> {
}
