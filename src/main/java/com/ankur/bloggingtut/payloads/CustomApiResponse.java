package com.ankur.bloggingtut.payloads;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class CustomApiResponse {
	
	private LocalDateTime timestamp;
	private List<String> messages;
	private boolean success;
	
	public CustomApiResponse(LocalDateTime timestamp, List<String> messages, boolean success) {
		super();
		this.timestamp = timestamp;
		this.messages = messages;
		this.success = success;
	}
}
