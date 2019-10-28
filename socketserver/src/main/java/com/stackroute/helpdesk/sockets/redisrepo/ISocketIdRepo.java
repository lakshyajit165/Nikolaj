package com.stackroute.helpdesk.sockets.redisrepo;

import com.stackroute.helpdesk.sockets.model.SocketStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocketIdRepo extends CrudRepository<SocketStore, String> {
}
