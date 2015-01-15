package ru.relex.pt.api.webservices.users;

import java.io.InputStream;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/users/avatar")
public class AvatarService {

	@GET()
	@Produces("image/png")
	public Response get(@QueryParam("userId") long userId)
	{
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("users/avatar/user"+userId+".png");
		ResponseBuilder response = Response.ok((Object) is);
		
		return response.build();
	}
}
