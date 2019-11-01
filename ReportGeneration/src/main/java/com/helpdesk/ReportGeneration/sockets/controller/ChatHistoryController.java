package com.helpdesk.ReportGeneration.sockets.controller;

import com.helpdesk.ReportGeneration.sockets.model.Chats;
import com.helpdesk.ReportGeneration.sockets.service.ChatStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/socketserver")
public class ChatHistoryController {

	@Autowired
	private ChatStoreService chatStoreService;

	@GetMapping("chats/{emailId}")
	public ResponseEntity getChatHistory(@PathVariable("emailId") String emailId){
		Chats chats = chatStoreService.getChatHistory(emailId);
		return new ResponseEntity(chats.getChatMessages(), HttpStatus.OK);
	}
}
