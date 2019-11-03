package com.helpdesk.ReportGeneration.sockets.service;

import com.helpdesk.ReportGeneration.sockets.model.SocketStore;
import com.helpdesk.ReportGeneration.sockets.redisrepo.ISocketIdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocketIdService implements ISocketIdService{

	@Autowired
	private ISocketIdRepo iSocketIdRepo;


	public void saveSocket(SocketStore socketStore){
		iSocketIdRepo.save(socketStore);
		List<SocketStore> socketStore1 = (List<SocketStore>) iSocketIdRepo.findAll();
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

	public void removeSocket(String emailId){
		iSocketIdRepo.deleteById(emailId);
	}
}
