package com.amar.restful.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amar.restful.messenger.database.Database;
import com.amar.restful.messenger.model.Comment;
import com.amar.restful.messenger.model.Message;

public class MessageService {
	private Map<Long, Message> messages = Database.getMessages();
	
	public MessageService() {
		Message m1  = new Message(1, "Message 1", "Amar" );
		m1.getComments().put(1L, new Comment(1L, "Comment 123", new Date(), "Amar"));
		messages.put(1L, m1);
		messages.put(2L, new Message(2, "Message 2", "Amar" ));
	}
	
	public List<Message> getMessages() {
		return new ArrayList<>(messages.values());
	}
	
	public List<Message> getMessagesByYear(int year) {
		List<Message> messagesList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				messagesList.add(message);
			}
		}
		return messagesList;
	}
	
	public List<Message> getMessagesPaginated(int start, int size) {
		List<Message> messagesList = new ArrayList<>(messages.values());
		if(start + size > messagesList.size()) {
			size = messagesList.size() - start;
		}
		return messagesList.subList(start, start + size);
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() < 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
}
