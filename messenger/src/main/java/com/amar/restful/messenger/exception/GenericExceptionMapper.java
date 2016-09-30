package com.amar.restful.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.amar.restful.messenger.model.ErrorMessage;

// Disabling to handle WebApplicationException
// @Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable arg0) {
		ErrorMessage errorMessage = new ErrorMessage("Server Error", 500, "www.yahoo.com");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

	
}
