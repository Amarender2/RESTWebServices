package com.amar.restful.messenger.messages;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	// Matrix Param :
	// http://localhost:8080/messenger/webapi/injectdemo/annotations;param=amar
	// Header Param : Key Value in Header
	// Cookie Param : Key Value in Cookies
	@GET
	@Path("annotations")
	public String getMessage(@MatrixParam("param") String param, @HeaderParam("customHeader") String headerValue,
			@CookieParam("JSESSIONID") String customCookie) {
		return "Matrix Param : " + param + "\nHeader Value : " + headerValue + "\nCustom Cookie : " + customCookie;
	}

	// @CookieParam
	@GET
	@Path("context")
	public String getMessage(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
		String path = uriInfo.getAbsolutePath().toString();
		String httpHeader = httpHeaders.getCookies().toString();
		return "Path : " + path + " HttpHeader : " + httpHeader;
	}

}
