package ru.relex.pt.api.webservices.exceptions;

public class IllegalOperationSequenceException extends ServiceException {

    public IllegalOperationSequenceException() {
	super("Illegal operations sequence");
    }

}
