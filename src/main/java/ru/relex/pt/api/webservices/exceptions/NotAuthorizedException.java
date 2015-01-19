package ru.relex.pt.api.webservices.exceptions;

public class NotAuthorizedException extends ServiceException {

    public NotAuthorizedException() {
	super("Not authorized user");
    }

}
