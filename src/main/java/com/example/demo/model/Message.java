package com.example.demo.model;

/**
 *
 * @author ard333
 */

public class Message {
	
	private String content;

	public Message(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message{" +
				"content='" + content + '\'' +
				'}';
	}
}
