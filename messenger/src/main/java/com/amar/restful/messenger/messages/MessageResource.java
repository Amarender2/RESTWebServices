package com.amar.restful.messenger.messages;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.amar.restful.messenger.model.Message;
import com.amar.restful.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
		if (messageFilterBean.getYear() > 0) {
			return messageService.getMessagesByYear(messageFilterBean.getYear());
		}
		if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0) {
			return messageService.getMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
		}

		return messageService.getMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessageById(@PathParam("messageId") long messageId) {
		return messageService.getMessage(messageId);
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message messageCreated = messageService.addMessage(message);
		String msgId = String.valueOf(messageCreated.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(msgId).build();
		// return Response.status(Status.CREATED).entity(messageCreated).build();
		return Response.created(uri).entity(messageCreated).build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
}
