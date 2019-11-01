package com.helpdesk.ReportGeneration.sockets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RedisHash("Socket")
public class SocketStore {

	@Id
	private String emailId;
	private String socketId;
	private String csrEmailId;
}
