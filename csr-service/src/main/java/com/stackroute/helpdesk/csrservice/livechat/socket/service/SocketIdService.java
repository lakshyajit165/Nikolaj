package com.stackroute.helpdesk.csrservice.livechat.socket.service;

import com.stackroute.helpdesk.csrservice.livechat.model.SocketStore;
import com.stackroute.helpdesk.csrservice.livechat.socket.redisrepo.ISocketIdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocketIdService implements ISocketIdService{

	@Autowired
	private ISocketIdRepo iSocketIdRepo;
//
//	@Autowired
//	SocketIdService(ISocketIdRepo iSocketIdRepo){
//		this.iSocketIdRepo = iSocketIdRepo;
//	}

	public void saveSocket(SocketStore socketStore){
		iSocketIdRepo.save(socketStore);
		List<SocketStore> socketStore1 = (List<SocketStore>) iSocketIdRepo.findAll();
	}
}
