package ru.relex.pt.api.webservices;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ru.relex.pt.api.webservices.exceptions.ServiceException;

@Provider
@Produces("text/plain")
public class ExceptionHandler implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException exception) {
	Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;

	if (exception instanceof ServiceException)
	    httpStatus = Response.Status.BAD_REQUEST;

	return Response.status(httpStatus).entity(exception.getMessage()).build();
    }
}