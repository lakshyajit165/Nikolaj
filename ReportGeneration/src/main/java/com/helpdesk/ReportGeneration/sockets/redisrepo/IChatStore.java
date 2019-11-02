package com.helpdesk.ReportGeneration.sockets.redisrepo;

import com.helpdesk.ReportGeneration.sockets.model.Chats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatStore extends CrudRepository<Chats, String>{
}
