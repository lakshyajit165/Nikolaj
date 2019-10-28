package com.stackroute.helpdesk.csrservice.livechat.model;

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
@RedisHash("SocketCsr")
public class SocketStore {

	@Id
	private String emailId;
	private String socketId;
}
