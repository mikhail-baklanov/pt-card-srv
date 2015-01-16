package ru.relex.pt.dto;

import java.util.Date;

public class PtUserMessages {
    private PtUsersDTO user; 
    private String text;
    private String sender;
    private Date date;
    
    public PtUserMessages(PtUsersDTO user, String sender, String text) {
	super();
	this.user = user;
	this.sender = sender;
	this.text = text;
    }

    public PtUsersDTO getUser() {
        return user;
    }

    public void setUser(PtUsersDTO user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
