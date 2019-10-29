package com.stackroute.helpdesk.sockets.redisrepo;

import com.stackroute.helpdesk.sockets.model.Chats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatStore extends CrudRepository<Chats, String>{
}
