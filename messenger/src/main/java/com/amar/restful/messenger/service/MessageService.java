package com.amar.restful.messenger.service;

import java.util.ArrayList;
import java.util.List;
import com.amar.restful.messenger.model.Message;

public class MessageService {

	public List<Message> getMessages() {
		List<Message> messages = new ArrayList<>();
		messages.add(new Message(1, "Message 1", "Amar"));
		messages.add(new Message(2, "Message 2", "Amar"));
		return messages;
	}
	
}
