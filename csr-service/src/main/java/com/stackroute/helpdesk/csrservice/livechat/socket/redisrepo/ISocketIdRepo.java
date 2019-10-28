package com.stackroute.helpdesk.csrservice.livechat.socket.redisrepo;

import com.stackroute.helpdesk.csrservice.livechat.model.SocketStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocketIdRepo extends CrudRepository<SocketStore, String> {
}
