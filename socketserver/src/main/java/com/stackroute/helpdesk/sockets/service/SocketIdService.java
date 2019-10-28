package com.stackroute.helpdesk.sockets.service;

import com.stackroute.helpdesk.sockets.model.SocketStore;
import com.stackroute.helpdesk.sockets.redisrepo.ISocketIdRepo;
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
		System.out.println("saved = " + socketStore1.get(0).getEmailId());
		System.out.println("saved = " + socketStore1.get(0).getSocketId());
	}

	public String getCsrEmailId(String emailId){
		Optional<SocketStore> optionalSocketStore = iSocketIdRepo.findById(emailId);
		SocketStore socketStore = null;
		if(optionalSocketStore.isPresent()) {
			socketStore = optionalSocketStore.get();
			return socketStore.getCsrEmailId();
		}
		else
		{
			return "";
		}
	}
}
