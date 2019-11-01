package com.helpdesk.ReportGeneration.sockets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "chats", timeToLive = 604800L)    //timeToLive = 604800L
public class Chats {

	@Id
	private String emailId;
	private List<ChatMessage> chatMessages;

}
