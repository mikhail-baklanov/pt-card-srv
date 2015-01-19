package ru.relex.pt.api.webservices.exceptions;

public class NotFoundEntranceException extends ServiceException {

    public NotFoundEntranceException() {
	super("Entrance id not found");
    }

}
